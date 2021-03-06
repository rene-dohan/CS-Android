package renetik.android.controller.extensions

import android.content.Intent
import android.view.View
import renetik.android.controller.base.CSActivityView
import renetik.android.controller.common.CSNavigationInstance.navigation
import renetik.android.java.event.listen
import renetik.android.java.event.listenOnce
import renetik.android.util.CSReachability

val CSActivityView<*>.intent: Intent get() = activity().intent

fun <T : View, ViewController : CSActivityView<T>>
        ViewController.push() = apply { navigation.push(this) }

fun <T : View, ViewController : CSActivityView<T>>
        ViewController.pushMain() = push("mainController")

fun <T : View, ViewController : CSActivityView<T>>
        ViewController.pushAsLast() = apply { navigation.pushAsLast(this) }

fun <T : View, ViewController : CSActivityView<T>>
        ViewController.push(pushKey: String) =
    apply { navigation.push(pushKey, this) }

var <T : View> CSActivityView<T>.requestedOrientation
    get() = activity().requestedOrientation
    set(value) {
        activity().requestedOrientation = value
    }

fun CSActivityView<*>.onInternetConnected(function: () -> Unit): CSReachability {
    val reachability = CSReachability().start()
    reachability.eventOnConnected.listenOnce { function() }
    onDestroy.listen { reachability.stop() }
    return reachability
}