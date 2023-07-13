package com.traccar
import com.facebook.react.bridge.ReactApplicationContext
import android.content.Intent
import android.util.Log
class TraccarClientBridge {

    fun startTrackingService(reactContext: ReactApplicationContext) {
        Log.i(TAG, "start!")
        try {
            val intent = Intent(reactContext, TrackingService::class.java)
            reactContext.startService(intent)
        } catch (e: Exception) {
            Log.e(TAG, "startTrackingService: " + e.message)
        }
    }
    fun stopTrackingService(reactContext: ReactApplicationContext) {
        Log.i(TAG, "stop!")
        try {
            val intent = Intent(reactContext, TrackingService::class.java)
            reactContext.stopService(intent)
        } catch (e: Exception) {
            Log.e(TAG, "startTrackingService: " + e.message)
        }
    }
    companion object {

        private val TAG = TraccarClientBridge::class.java.simpleName
    }
}
