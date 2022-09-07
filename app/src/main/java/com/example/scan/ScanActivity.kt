package com.example.scan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.scan.databinding.ActivityScanBinding
import com.example.scan.fragments.OnlineReportsFragment
import com.example.scan.fragments.PendingReportsFragment
import com.example.scan.fragments.ProfileFragment
import com.example.scan.fragments.ScanFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ScanActivity : AppCompatActivity() {
    //Instancias para nuestros fragentos
   /* private val scanFragment = ScanFragment()
    private val onlineReportsFragment = OnlineReportsFragment()
    private val pendingReportsFragment = PendingReportsFragment()
    private val profileFragment = ProfileFragment()
    private val activeFragment: Fragment = scanFragment*/

    private lateinit var binding: ActivityScanBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usuario = bundle?.get("usuario")


        val newBundle = Bundle()
        newBundle.putString("user", usuario as String?)

       // println("Imprimir-----> $newBundle y tambien----> $bundle")
       // supportFragmentManager.beginTransaction().replace(R.id.mainContainer,scanFragment).commit()*/

        //findNavController(R.id.mainContainer).setGraph(R.navigation.nav_graph, newBundle)



        //Controlando nav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
       // navHostFragment.arguments = newBundle //Agregado por mi
        navController = navHostFragment.navController

        navController.navigate(R.id.scanFragment, newBundle)
        //Con esto puedo pasar argumentos al fragments desde esta activity,
        // Yujuuuu!! 1 semana de trabajo para esta linea :')

       // val appBarConfiguration = AppBarConfiguration(setOf(R.id.scanFragment, R.id.onlineReportsFragment, R.id.pendingReportsFragment, R.id.profileFragment))

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)







    }







}