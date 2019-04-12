package com.silence.flickr.global

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.silence.flickr.R
import com.silence.flickr.global.extension.appContext

abstract class BaseFragment : MvpAppCompatFragment() {

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { restoreState(it) }
    }

    protected open fun restoreState(state: Bundle) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(layoutRes, container, false)

    internal fun showMessage(@StringRes message: Int, view: View) =
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

    internal fun showMessage(message: String,  view: View) =
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

    internal fun showMessageWithAction(@StringRes message: Int, @StringRes actionText: Int,  view: View, action: () -> Any) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { action.invoke() }
        snackBar.setActionTextColor(ContextCompat.getColor(appContext,
                R.color.colorAccent))
        snackBar.show()
    }

    open fun onBackPressed() {}
}