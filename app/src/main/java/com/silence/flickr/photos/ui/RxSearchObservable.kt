package com.silence.flickr.photos.ui

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.provider.SearchRecentSuggestions
import android.support.v7.widget.SearchView
import com.silence.flickr.global.extension.getItemAsString
import com.silence.flickr.photos.presentation.SearchHistoryProvider
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


object RxSearchObservable {
    fun fromSearchView(searchView: SearchView, context: Context): Observable<String> {

        val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val componentName = ComponentName(context, PhotosActivity::class.java)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        val subject = PublishSubject.create<String>()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                subject.onNext(s)

                val suggestions = SearchRecentSuggestions(
                    context,
                    SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE
                )
                suggestions.saveRecentQuery(s, null)

                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                return false
            }
        })

        return subject
    }

    fun getSuggestions(searchView: SearchView): Observable<String> {
        val subject = PublishSubject.create<String>()

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                searchView.setQuery(searchView.suggestionsAdapter.getItemAsString(), true)
                return true
            }
        })

        return subject
    }
}