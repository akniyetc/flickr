package com.silence.flickr.photos.domain.repository

import com.silence.flickr.photos.domain.entity.Photo
import io.reactivex.Single

interface PhotosRepository {
    fun loadPhotos(query: String?, page: Int): Single<List<Photo>>
}