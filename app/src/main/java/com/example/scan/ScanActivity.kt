package com.example.scan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.google.zxing.integration.android.IntentIntegrator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.scan.databinding.ActivityScanBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usuario = bundle?.get("usuario")

       /* val mBundle = Bundle()
        mBundle.putString("usuario", usuario.toString())

        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ScanFragment()

        mFragment.arguments = mBundle
        mFragmentTransaction.add(R.id.mainContainer, mFragment)*/

        //Controlando nav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)





    }







}