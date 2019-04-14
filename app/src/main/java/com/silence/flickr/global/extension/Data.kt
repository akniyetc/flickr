package com.silence.flickr.global.extension

import android.support.v4.widget.CursorAdapter
import com.silence.flickr.R
import com.silence.flickr.global.functional.Failure
import com.silence.flickr.global.system.ResourceManager
import com.silence.flickr.photos.domain.entity.Photo
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun Throwable.errorMessage(resourceManager: ResourceManager) = when (this) {
    is HttpException -> when (this.code()) {
        304 -> resourceManager.getString(R.string.not_modified_error)
        400 -> resourceManager.getString(R.string.bad_request_error)
        401 -> resourceManager.getString(R.string.unauthorized_error)
        403 -> resourceManager.getString(R.string.forbidden_error)
        404 -> resourceManager.getString(R.string.not_found_error)
        405 -> resourceManager.getString(R.string.method_not_allowed_error)
        409 -> resourceManager.getString(R.string.conflict_error)
        422 -> resourceManager.getString(R.string.unprocessable_error)
        500 -> resourceManager.getString(R.string.server_error_error)
        else -> resourceManager.getString(R.string.unknown_error)
    }
    is Failure.NetworkConnection -> resourceManager.getString(R.string.network_error)
    is SocketTimeoutException -> resourceManager.getString(R.string.timeout_error)
    else -> resourceManager.getString(R.string.unknown_error)
}


fun String.Companion.empty() = ""

fun Photo.url(): String {
    return "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"
}

fun CursorAdapter.getItemAsString(): String {
    return convertToString(cursor) as String
}