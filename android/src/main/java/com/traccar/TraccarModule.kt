package com.traccar

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise


class TraccarModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
  private val reactContext: ReactApplicationContext = reactContext

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun startTrackingService() {
    TraccarClientBridge().startTrackingService(this.reactContext)
  }

  @ReactMethod
  fun stopTrackingService() {
    TraccarClientBridge().stopTrackingService(this.reactContext)
  }

  companion object {
    const val NAME = "Traccar"
  }
}
