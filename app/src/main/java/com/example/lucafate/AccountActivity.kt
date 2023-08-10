package com.example.lucafate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class AccountActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.accountFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
    }
}