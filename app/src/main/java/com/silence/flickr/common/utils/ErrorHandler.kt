package com.silence.flickr.common.utils

import com.silence.flickr.common.system.ResourceManager
import com.silence.flickr.common.extension.errorMessage


class ErrorHandler(private val resourceManager: ResourceManager) {

    fun proceed(error: Throwable, messageListener: (String) -> Unit = {}) {
        messageListener(error.errorMessage(resourceManager))
    }
}