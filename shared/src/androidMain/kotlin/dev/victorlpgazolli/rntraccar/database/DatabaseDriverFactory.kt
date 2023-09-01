package dev.victorlpgazolli.rntraccar.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DriverFactory(private val appContext: Context) {
  actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(TraccarDatabase.Schema, appContext, "kmp.db")
  }
}
