package com.moviedb.app

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moviedb.core.navigation.Navigation
import com.moviedb.core.view.BaseActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class HomeActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var navHostFragment: Fragment
    private lateinit var navView: BottomNavigationView

    override val layout: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_main)

        navView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
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

    override fun navigate(screenName: String, bundle: Bundle) {
        when (screenName) {
            Navigation.ScreenName.MOVIE_DETAILS -> {
                //Can be use case of Deep links
                findNavController(R.id.nav_host_fragment).navigate(
                        R.id.nav_details, bundle
                )
            }
        }
    }

    override fun hideBottomNav() {
    }

    override fun showBottomNav() {
    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector
}