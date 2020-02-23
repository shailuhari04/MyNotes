package com.sdrss.mynotes.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.sdrss.mynotes.ui.NotesDialogHelper

/**
 * Below Extension Function for Logs.
 */
fun Any.showVLog(log: String) = Log.v(this::class.java.simpleName, log)

fun Any.showELog(log: String) = Log.e(this::class.java.simpleName, log)

fun Any.showDLog(log: String) = Log.d(this::class.java.simpleName, log)

fun Any.showILog(log: String) = Log.i(this::class.java.simpleName, log)

fun Any.showWLog(log: String) = Log.w(this::class.java.simpleName, log)


/**
 * Notes Dialog Extension Functions
 */
inline fun Activity.showNotesAlertDialog(func: NotesDialogHelper.() -> Unit): AlertDialog =
    NotesDialogHelper(this).apply {
        func()
    }.create()

inline fun Fragment.showNotesAlertDialog(func: NotesDialogHelper.() -> Unit): AlertDialog =
    NotesDialogHelper(this.context!!).apply {
        func()
    }.create()


/**
 * Helper Extension Function to check the internet connection
 */
fun Context.isOnline(): Boolean {
    var result = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    //Check if Connectivity manager is not NUll
    connectivityManager?.let {
        //We check the type of the connection is available for Internet
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
        } else {
            result = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false
        }
    }
    // Return result by default the value will be false
    return result
}


/**
 * Helper Extension Function to set the visibility (visible | invisible | gone of view)
 */
fun View.visible() {
    visibility = View.VISIBLE
    return
}

fun View.invisible() {
    visibility = View.INVISIBLE
    return
}

fun View.gone() {
    visibility = View.GONE
    return
}
