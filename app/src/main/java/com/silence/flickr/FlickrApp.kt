package com.silence.flickr

import android.app.Application
import android.content.ContextWrapper
import com.facebook.drawee.backends.pipeline.Fresco
import com.pixplicity.easyprefs.library.Prefs
import com.silence.flickr.global.di.appModule
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlickrApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initFresco()
        initDI()
        initPrefs()
        initLeakDetection()
    }

    private fun initFresco() {
        Fresco.initialize(this)
    }

    private fun initDI() {
        startKoin{
            androidContext(this@FlickrApp)
            modules(appModule)
        }
    }

    private fun initPrefs() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    private fun initLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}