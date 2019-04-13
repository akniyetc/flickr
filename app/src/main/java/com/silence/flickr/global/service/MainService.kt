package com.silence.flickr.global.service

import com.silence.flickr.global.service.Endpoints.PHOTOS
import com.silence.flickr.photos.domain.entity.PhotoHandler
import com.silence.flickr.photos.domain.entity.ResponseHandler
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    /*Photos---------------------------------------------------------------------------------------------*/
    @GET(PHOTOS)
    fun loadPhotos(
        @Query("method") method: String,
        @Query("text") query: String?,
        @Query("page") page: Int
    ): Single<ResponseHandler>

}