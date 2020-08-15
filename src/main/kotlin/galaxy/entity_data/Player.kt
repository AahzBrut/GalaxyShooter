package galaxy.entity_data

import javafx.geometry.Point2D

data class Player(
        val maxHealth: Int = 3,
        val numLives: Int = 3,
        val size: Double = 64.0,
        val maxSpeed: Double = 250.0,
        val acceleration: Double = 500.0,
        val rollSpeed: Double = .3,                             // time in seconds to fully roll in one direction
        val firingDelay: Double = .5,                           // cooldown between shots in seconds
        val mainWeaponPos: Point2D = Point2D(24.0, -20.0),
        val hitBoxScale: Double = 512.0 / 64.0
)

val PLAYER = Player()

data class LaserBolt(
        val maxHealth: Int = 1,
        val damage: Int = 1,
        val speed: Double = 300.0,
        val size: Point2D = Point2D(15.0, 30.0),
        val texture: String = "projectiles/laser.png",
        val shotSound: String = "laser_shot.wav"
)

val LASER_BOLT = LaserBolt()