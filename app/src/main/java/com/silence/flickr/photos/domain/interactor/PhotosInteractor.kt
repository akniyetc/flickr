package com.silence.flickr.photos.domain.interactor

import com.silence.flickr.global.system.SchedulersProvider
import com.silence.flickr.photos.domain.entity.Photo
import com.silence.flickr.photos.domain.repository.PhotosRepository
import io.reactivex.Single

class PhotosInteractor(private val photosRepository: PhotosRepository,
                       private val schedulers: SchedulersProvider) {

    fun getPhotos(query: String? = null, page: Int): Single<List<Photo>> =
            photosRepository.loadPhotos(query, page)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
}