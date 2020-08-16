package galaxy

import javafx.util.Duration

enum class PowerUpType(val activeTime: Duration, val animationName: String) {
    SHIELD(Duration.seconds(30.0), "shieldPowerUpAnim"),
    SPEED(Duration.seconds(30.0), "speedPowerUpAnim"),
    TRIPLE_SHOT(Duration.seconds(30.0), "tripleShotPowerUpAnim")
}