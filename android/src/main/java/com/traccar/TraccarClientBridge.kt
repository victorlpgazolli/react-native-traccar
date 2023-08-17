package com.traccar

import com.facebook.react.bridge.ReactApplicationContext
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.traccar.Constants.Companion.KEY_DEVICE
import com.traccar.Constants.Companion.KEY_INTERVAL
import com.traccar.Constants.Companion.KEY_URL


class TraccarClientBridge(context: ReactApplicationContext) {

  private var reactContext: ReactApplicationContext
  private var sharedPreferences: SharedPreferences
  private lateinit var alarmManager: AlarmManager
  private lateinit var alarmIntent: PendingIntent
  private var interval: Int = 15000

  init {
    reactContext = context
    sharedPreferences = reactContext.getSharedPreferences("main", Context.MODE_PRIVATE)
  }

    fun startTrackingService() {
        Log.i(TAG, "start!")
        try {
            val intent = Intent(reactContext, TrackingService::class.java)
            reactContext.startService(intent)
          ContextCompat.startForegroundService(reactContext, Intent(reactContext, TrackingService::class.java))
          if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            alarmManager.setInexactRepeating(
              AlarmManager.ELAPSED_REALTIME_WAKEUP,
              interval.toLong(), interval.toLong(), alarmIntent
            )
          }


        } catch (e: Exception) {
            Log.e(TAG, "startTrackingService: " + e.message)
        }
    }
    fun stopTrackingService() {
        Log.i(TAG, "stop!")
        try {
            val intent = Intent(reactContext, TrackingService::class.java)
            reactContext.stopService(intent)
        } catch (e: Exception) {
            Log.e(TAG, "startTrackingService: " + e.message)
        }
    }

    fun setupTrackingService(desiredUrl: String, desiredDeviceId: String, desiredInterval: Int) {
        Log.i(TAG, "setup!")
        try {
          interval = desiredInterval
          sharedPreferences.edit().putString(KEY_DEVICE, desiredDeviceId).apply()
          sharedPreferences.edit().putString(KEY_URL, desiredUrl).apply()
          sharedPreferences.edit().putInt(KEY_INTERVAL, interval).apply()
          alarmManager = reactContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
          val originalIntent = Intent(reactContext, AutostartReceiver::class.java)
          originalIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)

          val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
          } else {
            PendingIntent.FLAG_UPDATE_CURRENT
          }
          alarmIntent = PendingIntent.getBroadcast(reactContext, 0, originalIntent, flags)
        } catch (e: Exception) {
            Log.e(TAG, "setupTrackingService: " + e.message)
        }
    }

    companion object {

        private val TAG = "Traccar"+TraccarClientBridge::class.java.simpleName
    }

}
