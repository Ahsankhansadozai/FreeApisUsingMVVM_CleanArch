package com.example.multiviewsrecycler.base

import android.app.Application
import com.example.multiviewsrecycler.BuildConfig
import com.example.multiviewsrecycler.common.Constants.hTag
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

    private val context: BaseApplication
        get() = instance!!.context


    companion object {
        var instance: BaseApplication? = null
            private set

    }


    override fun onCreate() {
        super.onCreate()
        hInitTimber()

    }


    /*
    * Initializing Timber for logging purpose 
    * */

    private fun hInitTimber() {
        if (
            BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int,
                    tag: String?,
                    message: String,
                    t: Throwable?,
                ) {
                    super.log(priority, String.format(hTag, tag), message, t)
                }
            })
        }
    }


}