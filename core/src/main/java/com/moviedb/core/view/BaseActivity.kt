package com.moviedb.core.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.moviedb.core.R
import timber.log.Timber

/**
 * Created by Anil Gudigar on 21,February,2021
 */
abstract class BaseActivity : AppCompatActivity() {

    internal lateinit var contentLayout: View
    var progressBar: ProgressBar? = null

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!shouldUseDataBinding()) {
            setContentView(R.layout.activity_base)
            contentLayout = findViewById(R.id.content_frame)
            progressBar = findViewById(R.id.progressBarSmall)
        }
        getDataFromBundle()
        observeNetworkState()
    }

    abstract fun navigate(screenName: String, bundle: Bundle)
    abstract fun hideBottomNav()
    abstract fun showBottomNav()

    protected fun getDataFromBundle() {}

    protected fun observeNetworkState() {}

    protected fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgressBar()
        }
    }

    fun showProgress() {
        try {
            progressBar?.visibility = View.VISIBLE
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    /**
     * @return true if child activity should use data binding instead of [.setContentView]
     */
    protected open fun shouldUseDataBinding(): Boolean {
        return false
    }

    companion object {
        //Explicit Enabling of Vector Drawable Support for API-20 and bellow.
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

}

