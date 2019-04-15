package com.silence.flickr.common.service

import com.silence.flickr.photos.domain.entity.ResponseHandler
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    companion object {
        private const val SERVICES = "services/rest"
        const val PHOTOS = "$SERVICES?format=json&nojsoncallback=?"
    }

    @GET(PHOTOS)
    fun loadPhotos(
        @Query("method") method: String,
        @Query("text") query: String?,
        @Query("page") page: Int
    ): Single<ResponseHandler>
}