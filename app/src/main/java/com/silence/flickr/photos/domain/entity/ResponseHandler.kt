package com.silence.flickr.photos.domain.entity

data class ResponseHandler(
    val photos: PhotoHandler,
    val stat: String
)