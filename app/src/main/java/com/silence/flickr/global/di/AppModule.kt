package com.silence.flickr.global.di

import com.silence.flickr.photos.di.photoModule

val appModule = listOf(networkModule, utilModule, photoModule)