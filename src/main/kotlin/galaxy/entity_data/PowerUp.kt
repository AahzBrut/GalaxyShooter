package galaxy.entity_data

data class PowerUp(
    val size: Double = 64.0,
    val speed: Double = 25.0,
    val scoreToSpawn: Int = 1000
)

val POWER_UP = PowerUp()