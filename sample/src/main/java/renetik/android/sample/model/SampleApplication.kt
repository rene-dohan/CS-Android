package renetik.android.sample.model

import renetik.android.base.CSApplication
import renetik.android.base.application
import renetik.android.crashlitics.extensions.startFabricAnswers
import renetik.android.crashlitics.extensions.startFabricCrashlitics
import renetik.android.json.extensions.load
import renetik.android.sample.BuildConfig.DEBUG

val model by lazy { application.store.load(SampleModel::class, MODEL_KEY) }

class SampleApplication : CSApplication() {
    override val isDebugBuild = DEBUG
    override fun onCreate() {
        super.onCreate()
        startFabricCrashlitics()
        startFabricAnswers()
    }
}