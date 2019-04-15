package com.silence.flickr.photos.domain.entity

import com.google.gson.annotations.SerializedName

data class PhotoHandler (
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("photo") val photo: List<Photo>
)