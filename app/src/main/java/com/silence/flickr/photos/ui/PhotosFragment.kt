package com.silence.flickr.photos.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.silence.flickr.R
import com.silence.flickr.global.BaseFragment
import com.silence.flickr.global.EmptyViewHolder
import com.silence.flickr.global.extension.closeKeyboard
import com.silence.flickr.global.extension.visible
import com.silence.flickr.global.system.SchedulersProvider
import com.silence.flickr.photos.di.Scopes
import com.silence.flickr.photos.domain.entity.Photo
import com.silence.flickr.photos.presentation.PhotosPresenter
import com.silence.flickr.photos.presentation.PhotosView
import com.silence.flickr.photos.ui.adapter.PhotosAdapter
import com.stfalcon.frescoimageviewer.ImageViewer
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.layout_zero.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import java.util.concurrent.TimeUnit

class PhotosFragment : BaseFragment(), PhotosView {

    override val layoutRes = R.layout.fragment_photos

    private var emptyViewHolder: EmptyViewHolder? = null
    private val schedulers: SchedulersProvider by inject()

    private val adapter = PhotosAdapter { presenter.onPhotoClicked(it) }.apply {
        onBottomReachedListener = { presenter.loadNextPage() }
    }

    @InjectPresenter
    lateinit var presenter: PhotosPresenter

    @ProvidePresenter
    internal fun providePresenter(): PhotosPresenter {
        val scope = getKoin().getOrCreateScope(Scopes.PHOTOS, named(Scopes.PHOTOS))
        return scope.get()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        recyclerViewPhotos.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewPhotos.setHasFixedSize(true)
        recyclerViewPhotos.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener { presenter.refreshPhotos() }
        emptyViewHolder = EmptyViewHolder(zeroLayout) { presenter.refreshPhotos() }
        createSearchObservable()
    }

    @SuppressLint("CheckResult")
    private fun createSearchObservable() {
        context?.let {
            RxSearchObservable.fromSearchView(searchViewPhotos, it)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { s -> s.isNotEmpty() }
                .distinctUntilChanged()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe { s -> presenter.setQuery(s) }

            RxSearchObservable.getSuggestions(searchViewPhotos)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe()
        }
    }

    override fun showEmptyProgress(show: Boolean) {
        fullscreenProgressView.visible(show)
        recyclerViewPhotos.visible(!show)
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = false }
    }

    override fun showEmptyError(show: Boolean, message: String?) {
        if (show) emptyViewHolder?.showEmptyError(message)
        else emptyViewHolder?.hide()
    }

    override fun showEmptyView(show: Boolean) {
        if (show) emptyViewHolder?.showEmptyData()
        else emptyViewHolder?.hide()
    }

    override fun showPhotos(show: Boolean, photos: List<Photo>) {
        recyclerViewPhotos.visible(show)
        recyclerViewPhotos.post { adapter.setData(photos) }
    }

    override fun showMessage(message: String) {
        showMessage(message, swipeRefreshLayout)
    }

    override fun showRefreshProgress(show: Boolean) {
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = show }
    }

    override fun showPageProgress(show: Boolean) {
        recyclerViewPhotos.post { adapter.showProgress(show) }
    }

    override fun showFullScreen(url: String) {
        ImageViewer.Builder(context, arrayListOf(url)).show()
    }

    override fun hideKeyboard() {
        closeKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        getKoin().getScopeOrNull(Scopes.PHOTOS)?.close()
    }
}