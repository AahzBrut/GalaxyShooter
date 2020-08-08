package galaxy.entities

import javafx.geometry.Point2D

data class Player(
        val numLives: Int = 3,
        val size: Double = 64.0,
        val maxSpeed: Double = 10.0,
        val acceleration: Double = 10.0,
        val rollSpeed: Double = .3,                             // time in seconds to fully roll in one direction
        val firingDelay: Double = .5,                           // cooldown between shots in seconds
        val projectileSpeed: Double = 40.0,
        val mainWeaponPos: Point2D = Point2D(24.0, -20.0)
)

val PLAYER = Player()

data class LaserBolt(
        val speed: Double = 100.0,
        val size: Point2D = Point2D(15.0,30.0)
)

val LASER_BOLT = LaserBolt()