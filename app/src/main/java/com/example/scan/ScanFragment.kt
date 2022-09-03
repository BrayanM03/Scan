package com.example.scan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.scan.databinding.ActivityScanBinding
import com.example.scan.databinding.FragmentScanBinding
import com.google.zxing.integration.android.IntentIntegrator
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScanFragment : Fragment() {

    private var _binding : FragmentScanBinding? = null
    private val binding get() = _binding!!

   /* private var param1: String? = null
    private var param2: String? = null*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScanBinding.inflate(inflater, container, false)

       /* val bundle = arguments
        val usuario = bundle?.getString("usuario")
        println("Se paso satifactoriamente la cadena: $usuario y esto es el bundel $bundle")*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
       /* arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/


        binding.btnScanner.setOnClickListener { initScanner()}


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(arguments != null){
            val user = requireArguments().getString("usuario")
            println("datos recibidos $user")
        }

    }

    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/

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
            super.onActivityResult(requestCode, resultCode, data)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}