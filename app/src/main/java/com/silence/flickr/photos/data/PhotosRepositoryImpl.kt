package com.silence.flickr.photos.data

import com.silence.flickr.global.functional.Failure.NetworkConnection
import com.silence.flickr.global.functional.NetworkHandler
import com.silence.flickr.global.service.MainService
import com.silence.flickr.photos.domain.entity.Photo
import com.silence.flickr.photos.domain.repository.PhotosRepository
import io.reactivex.Single

class PhotosRepositoryImpl(
    private val service: MainService,
    private val networkHandler: NetworkHandler
) : PhotosRepository {

    override fun loadPhotos(query: String?, page: Int): Single<List<Photo>> {
        val method = if (query == null || query.isEmpty()) METHOD_RECENT else METHOD_SEARCH

        return when (networkHandler.isConnected) {
            true -> {
                service.loadPhotos(method, query, page)
                    .map { it.photos.photo }
            }
            false, null -> Single.error(NetworkConnection())
        }
    }

    companion object {
        private const val METHOD = "flickr.photos"
        const val METHOD_RECENT = "$METHOD.getRecent"
        const val METHOD_SEARCH = "$METHOD.search"
    }
}