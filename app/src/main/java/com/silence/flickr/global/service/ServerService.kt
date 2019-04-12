package com.silence.flickr.global.service

import com.silence.flickr.global.service.Endpoints.TOKEN
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerService {

    /*Auth---------------------------------------------------------------------------------------------*/
    @POST(TOKEN)
    fun loadToken(@Body idToken: String): Single<Boolean>

}