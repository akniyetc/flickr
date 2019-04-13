package com.silence.flickr.global.system

import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.silence.flickr.photos.ui.fullscreen.PhotoFullScreenActivity

class Router {

    fun showPhotoFullScreen(activity: FragmentActivity, url: String, navigationExtras: Extras) {
        val intent = PhotoFullScreenActivity.callingIntent(activity, url)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    class Extras(val transitionSharedElement: View)
}