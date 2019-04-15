package com.silence.flickr.common.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.silence.flickr.BuildConfig
import com.silence.flickr.common.di.ServiceProperties.API_KEY_QUERY
import com.silence.flickr.common.di.ServiceProperties.KEY
import com.silence.flickr.common.di.ServiceProperties.SERVER_URL
import com.silence.flickr.common.service.MainService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { createOkHttpClient() }
    single { createWebService<MainService>(get(), SERVER_URL) }
}

object ServiceProperties {
    const val SERVER_URL = "https://api.flickr.com/"
    const val API_KEY_QUERY = "api_key"
    const val KEY = "84b9c142602a0eef6589f196731da212"
}

fun createOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()

    okHttpClientBuilder.addInterceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter(API_KEY_QUERY, KEY)
        request = request.newBuilder()
            .url(url.build())
            .build()
        chain.proceed(request)
    }

    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}