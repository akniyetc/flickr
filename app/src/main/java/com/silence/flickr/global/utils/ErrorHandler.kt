package com.silence.flickr.global.utils

import com.silence.flickr.global.system.ResourceManager
import com.silence.flickr.global.extension.errorMessage


class ErrorHandler(private val resourceManager: ResourceManager) {

    fun proceed(error: Throwable, messageListener: (String) -> Unit = {}) {
        messageListener(error.errorMessage(resourceManager))
    }
}