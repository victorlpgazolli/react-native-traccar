package dev.victorlpgazolli.rntraccar.database

import app.cash.sqldelight.db.SqlDriver


expect class DriverFactory {
  fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): TraccarDatabase {
  val driver = driverFactory.createDriver()
  val database = TraccarDatabase(driver)

  return database
  // Do more work with the database (see below).
}
