package com.moviedb.core

import android.app.Application

/**
 * Created by Anil Gudigar on 20,February,2021
 */
class Contextor {
    var context: Application? = null

    fun init(pContext: Application) {
        context = pContext
    }

    companion object {

        private var sInstance: Contextor? = null

        val instance: Contextor
            get() {
                if (sInstance == null) {
                    sInstance = Contextor()
                }
                return sInstance!!
            }
    }
}