package dev.victorlpgazolli.rntraccar.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AndroidMainApp {
  // since we are using the applicationContext and not
  // the context per se, nothing is going to leak here
  lateinit var applicationContext: Context
}
