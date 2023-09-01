package dev.victorlpgazolli.rntraccar.providers

import dev.victorlpgazolli.rntraccar.database.DriverFactory
import dev.victorlpgazolli.rntraccar.database.TraccarDatabase


class DataSourceProvider(driverFactory: DriverFactory) {

  private val database = TraccarDatabase(driverFactory.createDriver())

  fun getLocalCommonDatabase() = database
}
