package com.example.scan.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.scan.Data
import com.example.scan.R
import com.example.scan.ResponseItem
import com.example.scan.ScanActivity
import com.example.scan.databinding.ActivityScanBinding
import com.example.scan.databinding.FragmentScanBinding
import com.example.scan.model.APIService
import com.example.scan.model.RestEngine
import com.example.scan.viewmodel.ScanFragmentViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import kotlin.math.log


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TAG = "ScanActivity"
private const val TEXT_CONTENTS = "TextContent"

/**
 * A simple [Fragment] subclass.
 * Use the [ScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScanFragment : Fragment() {

    private val viewModel by navGraphViewModels<ScanFragmentViewModel>(R.id.nav_graph)
    private var _binding : FragmentScanBinding? = null
    private val binding get() = _binding!!
    lateinit var mContext: Context

   /* private var param1: String? = null
    private var param2: String? = null*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScanBinding.inflate(inflater, container, false)


        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val data = arguments
        val usuario = data!!.getString("user")
        if(usuario != null){

            setSaludo(usuario)
        }

        binding.btnScanner.setOnClickListener { initScanner()}


    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "OnSaveInstanceState: called")
        println("Se llamo la onSaveInstance")
        super.onSaveInstanceState(outState)
        outState?.putString(TEXT_CONTENTS, binding.tvEmptyCard.text.toString())

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?){
        Log.d(TAG, "onRestoreInstanceState: called")
        println("Se llamo  onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
        val saveString = savedInstanceState?.getString(TEXT_CONTENTS, "Holis")
        binding.tvEmptyCard?.text = saveString
    }

    //Saludar al usuario
    private fun setSaludo(usuario: String){
        CoroutineScope(Dispatchers.IO).launch {

            val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
            val result = apiService.obtenerUsuario(usuario)



            (mContext as Activity).runOnUiThread{

                val bodyResponse = result.body()
                val bodyData = bodyResponse?.data?.get(0)
                val userName = bodyData?.nombre
                val userLastname = bodyData?.apellido
                val urlProfileImage = bodyData?.image_url
                val userCompleteName = "$userName $userLastname"
                val tvUserName = binding.tvUserName
                val ivUser = binding.ivProfile
                tvUserName.text = userCompleteName
                Glide.with(this@ScanFragment).load(urlProfileImage).into(ivUser)




            }




        }

    }

    //Funciones del escaner
    //Proceso del escaner
    private fun initScanner() {
        println("Funciono")
        val integrator = IntentIntegrator(requireContext() as Activity?)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val appContext = requireContext().applicationContext


        if(result != null){
            if(result.contents == null){
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show()
            }else{
                //Toast.makeText(requireContext(), "El valor escaneado es: ${result.contents}", Toast.LENGTH_SHORT).show()
                MotionToast.createToast(
                    appContext as Activity,
                    "Error",
                    result.contents,
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    null)
            }
        }else{
            super. onActivityResult(requestCode, resultCode, data)
        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        //viewModel.listState =
        _binding = null
    }


}