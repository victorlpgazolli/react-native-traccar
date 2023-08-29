package dev.victorlpgazolli.rntraccar.database


import com.squareup.sqldelight.db.SqlDriver
import dev.victorlpgazolli.rntraccar.models.Position

// 1) COMO DEFINIR DEPENDÊNCIAS DE TESTE INSTRUMENTADO
// 2) COMO ATUALIZAR O BUILD.GRADLE E RESOLVER POSSÍVEIS PROBLEMAS
// 3) COMO CRIAR PACOTE DE TESTE INSTRUMENTADO
// 4) COMO CRIAR TESTE INSTRUMENTADO PARA O BANCO DE DADOS (REQUER CONTEXTO)
class Database(driver: SqlDriver) {
  private val database = CommonDatabase(driver)
  private val dbQuery = database.appDatabaseQueries

  fun deletePosition(id: String) {
    dbQuery.transaction {
      dbQuery.deletePosition(id)
    }
  }


  fun selectPosition(): List<Position> {
    return dbQuery.selectPosition().executeAsList()
  }


  fun insertPosition(position: Position) {
    dbQuery.transaction {
      dbQuery.insertPosition(
        deviceId = position.deviceId,
        time = position.time,
        latitude = position.latitude,
        longitude = position.longitude,
        altitude = position.altitude,
        speed = position.speed,
        course = position.course,
        accuracy = position.accuracy,
        battery = position.battery,
        charging = position.charging,
        mock= position.mock
      )
    }
  }


}
