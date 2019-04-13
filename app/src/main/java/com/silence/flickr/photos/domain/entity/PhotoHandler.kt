package com.silence.flickr.photos.domain.entity

data class PhotoHandler (
    val photo: List<Photo>,
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int
)