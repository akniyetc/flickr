package com.silence.flickr.photos.di

import com.silence.flickr.photos.data.PhotosRepositoryImpl
import com.silence.flickr.photos.domain.interactor.PhotosInteractor
import com.silence.flickr.photos.domain.repository.PhotosRepository
import com.silence.flickr.photos.presentation.PhotosPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val photoModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(get(), get()) }
    single { PhotosInteractor(get(), get()) }
    scope(named(Scopes.PHOTOS)) {
        scoped { PhotosPresenter(get(), get()) }
    }
}

object Scopes {
    const val PHOTOS = "photos_scope"
}