package com.silence.flickr.global.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.LayoutRes
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.silence.flickr.global.BaseActivity
import com.silence.flickr.global.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ImageView.loadFromUrl(url: String) =
        Glide.with(this.context.applicationContext)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

fun BaseFragment.close() = fragmentManager?.popBackStack()

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.cancelTransition() {
        transitionName = null
}

fun ImageView.cancelPostponeEnterTransition(activity: FragmentActivity) {
        val target: Target<Drawable> = ImageViewBaseTarget(this,
                activity)
        Glide.with(context.applicationContext).load("").into(target)
}

private class ImageViewBaseTarget (var imageView: ImageView?, var activity: FragmentActivity?) : BaseTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                imageView?.setImageDrawable(resource)
                activity?.supportStartPostponedEnterTransition()
        }

        override fun removeCallback(cb: SizeReadyCallback) {
                imageView = null
                activity = null
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                activity?.supportStartPostponedEnterTransition()
        }

        override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
}