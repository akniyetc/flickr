package com.silence.flickr.global.di


import com.silence.flickr.global.functional.NetworkHandler
import com.silence.flickr.global.system.AppSchedulers
import com.silence.flickr.global.system.ResourceManager
import com.silence.flickr.global.system.Router
import com.silence.flickr.global.system.SchedulersProvider
import com.silence.flickr.global.utils.ErrorHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilModule = module {
    single { AppSchedulers() as SchedulersProvider }
    single { ResourceManager(androidContext()) }
    single { ErrorHandler(get()) }
    single { NetworkHandler(androidContext()) }
    single { Router() }
}