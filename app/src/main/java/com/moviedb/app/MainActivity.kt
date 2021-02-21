package com.moviedb.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener {
            navController.popBackStack()
            when (it.itemId) {
                R.id.navigation_movies -> {
                    navController.navigate(R.id.navigation_movies)
                }
                R.id.navigation_tvshows -> {
                    navController.navigate(R.id.navigation_tvshows)
                }
                R.id.navigation_people -> {
                    navController.navigate(R.id.navigation_people)
                }
                R.id.navigation_more -> {
                    navController.navigate(R.id.navigation_more)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }
}