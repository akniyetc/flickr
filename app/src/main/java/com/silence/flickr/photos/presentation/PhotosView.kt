package com.silence.flickr.photos.presentation

import com.arellomobile.mvp.MvpView
import com.silence.flickr.photos.domain.entity.Photo

interface PhotosView: MvpView {
    fun showEmptyProgress(show: Boolean)
    fun showEmptyError(show: Boolean, message: String?)
    fun showEmptyView(show: Boolean)
    fun showPhotos(show: Boolean, photos: List<Photo>)
    fun showMessage(message: String)
    fun showRefreshProgress(show: Boolean)
    fun showPageProgress(show: Boolean)
    fun showFullScreen(url: String)
    fun hideKeyboard()
}