package com.silence.flickr.photos.presentation

import android.content.SearchRecentSuggestionsProvider

class SearchHistoryProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        val AUTHORITY = SearchHistoryProvider::class.java.name
        val MODE = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }
}