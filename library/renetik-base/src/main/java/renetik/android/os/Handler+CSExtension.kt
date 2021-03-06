package renetik.android.os

import android.os.Handler
import android.os.Looper
import renetik.android.java.extensions.exception
import renetik.android.logging.CSLog.logError

object CSHandler {
    val main by lazy { Handler(Looper.getMainLooper()) }

    fun post(function: () -> Unit) {
        if (!main.post(function)) logError(exception("Runnable not run"))
    }

    fun post(delay: Long, function: () -> Unit) {
        if (!main.postDelayed(function, delay)) logError(exception("Runnable not run"))
    }

    fun post(delay: Int, function: () -> Unit) {
        post(delay.toLong(), function)
    }

    fun removePosted(function: () -> Unit) {
        main.removeCallbacks(function)
    }

}

