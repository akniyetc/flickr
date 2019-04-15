package com.silence.flickr.common.di


import com.silence.flickr.common.functional.NetworkHandler
import com.silence.flickr.common.system.AppSchedulers
import com.silence.flickr.common.system.ResourceManager
import com.silence.flickr.common.system.SchedulersProvider
import com.silence.flickr.common.utils.ErrorHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilModule = module {
    single { AppSchedulers() as SchedulersProvider }
    single { ResourceManager(androidContext()) }
    single { ErrorHandler(get()) }
    single { NetworkHandler(androidContext()) }
}