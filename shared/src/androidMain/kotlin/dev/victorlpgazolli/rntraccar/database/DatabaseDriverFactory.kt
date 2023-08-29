package dev.victorlpgazolli.rntraccar.database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dev.victorlpgazolli.rntraccar.utils.AndroidMainApp

actual fun createSqlDriver(): SqlDriver {
  return AndroidSqliteDriver(CommonDatabase.Schema, AndroidMainApp.applicationContext, "common.db")
}
