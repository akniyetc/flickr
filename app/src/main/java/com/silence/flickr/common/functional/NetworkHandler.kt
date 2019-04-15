package com.silence.flickr.common.functional

import android.content.Context
import com.silence.flickr.common.extension.networkInfo

class NetworkHandler(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}