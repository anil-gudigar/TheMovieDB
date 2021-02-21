package com.moviedb.app

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.moviedb.app.di.AppInjector
import com.moviedb.core.BaseApp
import com.moviedb.core.Contextor
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Anil Gudigar on 21,February,2021
 */
class TheMovieDBApp : BaseApp(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {
        var context: Context? = null
        var instance: TheMovieDBApp? = null
        fun sharedInstance(): TheMovieDBApp {
            if (instance == null) {
                instance = TheMovieDBApp()
            }
            return instance as TheMovieDBApp
        }

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        //Stetho helps debug network calls and database / preference values
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        AppInjector.init(this)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        setAppinstance()
    }

    private fun setAppinstance() {
        context = this
        Contextor.instance.init(this)
    }
}