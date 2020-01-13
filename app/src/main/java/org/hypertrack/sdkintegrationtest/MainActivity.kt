package org.hypertrack.sdkintegrationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hypertrack.sdk.HyperTrack
import com.hypertrack.sdk.TrackingError
import com.hypertrack.sdk.TrackingStateObserver

class MainActivity : AppCompatActivity(), TrackingStateObserver.OnTrackingStateChangeListener {

    lateinit var sdkInstance: HyperTrack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        sdkInstance = HyperTrack
            .getInstance(applicationContext, HT_KEY)
            .addTrackingListener(this as TrackingStateObserver.OnTrackingStateChangeListener)

    }

    fun onClick(view: View) {
        if (view.id == R.id.button) {
            if (sdkInstance.isRunning) {
                sdkInstance.stop()
            } else {
                sdkInstance.start()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        sdkInstance.stop()
    }

    override fun onTrackingStart() {
        Log.d(TAG, "onTrackingStart with device id ${sdkInstance.deviceID}")
    }

    override fun onError(error: TrackingError?) {
        Log.d(TAG, "onError: $error")
    }

    override fun onTrackingStop() {
        Log.d(TAG, "onTrackingStop")
    }

    companion object {
        val HT_KEY = "uvIAA8xJANxUxDgINOX62-LINLuLeymS6JbGieJ9PegAPITcr9fgUpROpfSMdL9kv-qFjl17NeAuBHse8Qu9sw"
        val TAG = "MainActivity"
    }
}
