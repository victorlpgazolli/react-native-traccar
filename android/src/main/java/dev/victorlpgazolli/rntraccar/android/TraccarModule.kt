package dev.victorlpgazolli.rntraccar.android


import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise


class TraccarModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {
  private val reactContext: ReactApplicationContext

  private lateinit var traccarClient: TraccarClientBridge

  init {
    reactContext = context
    traccarClient = TraccarClientBridge(reactContext)
  }
  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun startTrackingService() {
    traccarClient.startTrackingService()
  }

  @ReactMethod
  fun stopTrackingService() {
    traccarClient.stopTrackingService()
  }

  @ReactMethod
  fun setupTrackingService(url: String, deviceId: String, interval: Int) {
    traccarClient.setupTrackingService(url, deviceId, interval)
  }

  companion object {
    const val NAME = "Traccar"
  }
}
