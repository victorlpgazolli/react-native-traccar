package dev.victorlpgazolli.rntraccar.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Position(
  @SerialName("id")
  val id: Long,
  @SerialName("deviceId")
  val deviceId: String,
  @SerialName("time")
  val time: Long,
  @SerialName("latitude")
  val latitude: Double,
  @SerialName("longitude")
  val longitude: Double,
  @SerialName("altitude")
  val altitude: Double,
  @SerialName("speed")
  val speed: Double,
  @SerialName("course")
  val course: Double,
  @SerialName("accuracy")
  val accuracy: Double,
  @SerialName("battery")
  val battery: Double,
  @SerialName("charging")
  val charging: Boolean,
  @SerialName("mock")
  val mock: Boolean,
)


