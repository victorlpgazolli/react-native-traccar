package dev.victorlpgazolli.rntraccar.providers

import dev.victorlpgazolli.rntraccar.database.Database
import dev.victorlpgazolli.rntraccar.database.createSqlDriver

class DataSourceProvider {
  private val database = Database(createSqlDriver())

  fun getLocalCommonDatabase() = database
}
