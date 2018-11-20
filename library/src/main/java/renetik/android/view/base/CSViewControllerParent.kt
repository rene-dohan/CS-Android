package renetik.android.view.base

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import renetik.android.view.base.CSActivityResult
import renetik.android.view.base.CSOnKeyDownResult
import renetik.android.view.base.CSRequestPermissionResult
import renetik.java.event.CSEvent
import renetik.java.lang.CSValue
import renetik.android.view.menu.CSOnMenu
import renetik.android.view.menu.CSOnMenuItem

interface CSViewControllerParent {
    val onCreate: CSEvent<Bundle?>
    val onSaveInstanceState: CSEvent<Bundle>
    val onStart: CSEvent<Unit>
    val onResume: CSEvent<Unit>
    val onPause: CSEvent<Unit>
    val onStop: CSEvent<Unit>
    val onDestroy: CSEvent<Unit>
    val onBack: CSEvent<CSValue<Boolean>>
    val onConfigurationChanged: CSEvent<Configuration>
    val onLowMemory: CSEvent<Unit>
    val onUserLeaveHint: CSEvent<Unit>
    val onPrepareOptionsMenu: CSEvent<CSOnMenu>
    val onOptionsItemSelected: CSEvent<CSOnMenuItem>
    val onCreateOptionsMenu: CSEvent<CSOnMenu>
    val onActivityResult: CSEvent<CSActivityResult>
    val onKeyDown: CSEvent<CSOnKeyDownResult>
    val onNewIntent: CSEvent<Intent>
    val onRequestPermissionsResult: CSEvent<CSRequestPermissionResult>
    val onViewVisibilityChanged: CSEvent<Boolean>
    fun activity(): AppCompatActivity
}