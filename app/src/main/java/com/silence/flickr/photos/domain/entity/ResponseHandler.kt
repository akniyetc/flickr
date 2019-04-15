package com.silence.flickr.photos.domain.entity

import com.google.gson.annotations.SerializedName

data class ResponseHandler(
    @SerializedName("photos") val photos: PhotoHandler,
    @SerializedName("stat") val stat: String
)