package com.silence.flickr.common.system

import android.content.Context
import android.support.v4.content.ContextCompat

class ResourceManager (private val context: Context) {

    fun getString(id: Int) = context.getString(id)

    fun getColor(id: Int) = ContextCompat.getColor(context, id)
}