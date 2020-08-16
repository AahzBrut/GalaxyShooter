package galaxy.entity_data

data class PowerUp(
    val size: Double = 64.0,
    val speed: Double = 25.0,
    val scoreToSpawn: Int = 1000,
    val activateSound: String = "power_up_sound.wav"
)

val POWER_UP = PowerUp()