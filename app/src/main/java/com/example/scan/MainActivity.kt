package com.example.scan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.scan.databinding.ActivityMainBinding
import com.example.scan.model.APIService
import com.example.scan.model.RestEngine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Scan)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnIniciarSesion.setOnClickListener { iniciarSesion() }

    }

    private fun iniciarSesion() {
        var imageViewTest = binding.buttomIV
        loginAnimation(imageViewTest, R.raw.load_yellow)

        val etUser = binding.etUser.text
        val etPass = binding.etPassword.text
        val etUserString = etUser.toString()
        val etPassString = etPass.toString()

        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val result: Call<ResponseItem> = apiService.validarUsuario(etUserString, etPassString)

        result.enqueue(object : Callback<ResponseItem> {

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Llamada fallo: ${t}", Toast.LENGTH_LONG).show()
                println("Llamada fallo: ${t}")
            }

            override fun onResponse(
                call: Call<ResponseItem>,
                response: Response<ResponseItem>
            ) {
                val responseBody = response.body()!!
                val responseEstatus = responseBody.estatus

                if(responseEstatus != false){
                    MotionToast.createToast(this@MainActivity,
                        "Correcto",
                        responseBody.mensaje,
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        null)

                    var intentSA = Intent(this@MainActivity, ScanActivity::class.java)

                    intentSA.putExtra("usuario", etUserString)
                    intentSA.putExtra("password", etPassString)
                    startActivity(intentSA)

                    //validarSesionActual()

                }else{
                    MotionToast.createToast(this@MainActivity,
                        "Error",
                        responseBody.mensaje,
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        null)


                }

                binding.btnIniciarSesion.text = "Entrar"
                imageViewTest.frame = 0
                imageViewTest.pauseAnimation()




            }


        })


    }

    private fun loginAnimation(imageView: LottieAnimationView, animation: Int){
        binding.btnIniciarSesion.text = "Inicando.."

        imageView.setAnimation(animation)
        imageView.playAnimation()
    }

}