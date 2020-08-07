package galaxy.entities

data class Player(
        val numLives: Int = 3,
        val speed: Double  = 10.0,
        val size: Double = 64.0,
        val maxSpeed: Double = 10.0,
        val acceleration: Double = 10.0
)

val PLAYER = Player()