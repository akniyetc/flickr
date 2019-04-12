package com.silence.flickr.global.functional

import android.content.Context
import com.silence.flickr.global.extension.networkInfo

class NetworkHandler(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}