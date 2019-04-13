package com.silence.flickr.photos.domain.entity

data class Photo constructor(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int
)