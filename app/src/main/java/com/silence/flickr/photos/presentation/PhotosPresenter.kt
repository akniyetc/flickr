package com.silence.flickr.photos.presentation

import com.arellomobile.mvp.InjectViewState
import com.silence.flickr.global.extension.url
import com.silence.flickr.global.presentation.BasePresenter
import com.silence.flickr.global.presentation.Paginator
import com.silence.flickr.global.system.Router
import com.silence.flickr.global.utils.ErrorHandler
import com.silence.flickr.photos.domain.entity.Photo
import com.silence.flickr.photos.domain.interactor.PhotosInteractor

@InjectViewState
class PhotosPresenter(
    private val interactor: PhotosInteractor,
    private val errorHandler: ErrorHandler
) : BasePresenter<PhotosView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        refreshPhotos()
    }

    private var query: String? = null

    private val paginator = Paginator(
        { interactor.getPhotos(query, it) },
        object : Paginator.ViewController<Photo> {
            override fun showEmptyProgress(show: Boolean) {
                viewState.showEmptyProgress(show)
            }

            override fun showEmptyError(show: Boolean, error: Throwable?) {
                if (error != null) {
                    errorHandler.proceed(error) {viewState.showEmptyError(show, it)}
                } else {
                    viewState.showEmptyError(show, null)
                }
            }

            override fun showEmptyView(show: Boolean) {
                viewState.showEmptyView(show)
            }

            override fun showData(show: Boolean, data: List<Photo>) {
                viewState.showPhotos(show, data)
            }

            override fun showErrorMessage(error: Throwable) {
                errorHandler.proceed(error) {viewState.showMessage(it)}
            }

            override fun showRefreshProgress(show: Boolean) {
                viewState.showRefreshProgress(show)
            }

            override fun showPageProgress(show: Boolean) {
                viewState.showPageProgress(show)
            }
        }
    )

    fun setQuery(text: String) {
        this.query = text
    }

    fun refreshPhotos() = paginator.refresh()
    fun loadNextPage() = paginator.loadNewPage()

    fun onPhotoClicked(photo: Photo, extras: Router.Extras) {
        viewState.showFullScreen(photo.url(), extras)
    }

    override fun onDestroy() {
        super.onDestroy()
        paginator.release()
    }

}