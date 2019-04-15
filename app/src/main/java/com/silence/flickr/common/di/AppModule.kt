package com.silence.flickr.common.di

import com.silence.flickr.photos.di.photoModule

val appModule = listOf(networkModule, utilModule, photoModule)