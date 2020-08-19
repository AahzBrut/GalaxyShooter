package galaxy.components

import com.almasb.fxgl.dsl.getAppHeight
import com.almasb.fxgl.dsl.getAppWidth
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.input.UserAction
import galaxy.DOWN
import galaxy.LEFT
import galaxy.RIGHT
import galaxy.UP
import galaxy.entity_data.PLAYER
import javafx.geometry.Point2D
import kotlin.math.abs
import kotlin.math.sign

class PlayerMovementComponent : Component() {

    private val maxSpeed: Double = PLAYER.maxSpeed
    private val acceleration: Double = PLAYER.acceleration
    private var currentVelocity: Point2D = Point2D.ZERO

    var currentDirection: Point2D = Point2D.ZERO

    fun forwardThrusterBegin() { currentDirection = Point2D(currentDirection.x, UP.y) }
    fun forwardThrusterEnd() { currentDirection = Point2D(currentDirection.x, 0.0) }

    fun backThrusterBegin() { currentDirection = Point2D(currentDirection.x, DOWN.y) }
    fun backThrusterEnd() { currentDirection = Point2D(currentDirection.x, 0.0) }

    fun rightThrusterBegin() { currentDirection = Point2D(RIGHT.x, currentDirection.y) }
    fun rightThrusterEnd() { currentDirection = Point2D(0.0, currentDirection.y) }

    fun leftThrusterBegin() { currentDirection = Point2D(LEFT.x, currentDirection.y) }
    fun leftThrusterEnd() { currentDirection = Point2D(0.0, currentDirection.y) }

    private fun getVelocity(tpf: Double): Point2D {

        val dx = if (currentDirection.x == 0.0) {
            getDecelVelocity(currentVelocity.x, tpf)
        } else {
            getAccelVelocity(currentVelocity.x, currentDirection.x, tpf)
        }
        val dy = if (currentDirection.y == 0.0) {
            getDecelVelocity(currentVelocity.y, tpf)
        } else {
            getAccelVelocity(currentVelocity.y, currentDirection.y, tpf)
        }
        return Point2D(dx, dy)
    }

    private fun getDecelVelocity(vel: Double, tpf: Double) =
            if (vel > 0.0) {
                if (vel - acceleration * tpf < 0.0)
                    0.0
                else
                    vel - acceleration * tpf
            } else {
                if (vel + acceleration * tpf > 0.0)
                    0.0
                else
                    vel + acceleration * tpf
            }

    private fun getAccelVelocity(vel: Double, dir: Double, tpf: Double) =
            if (abs(vel + dir * acceleration * tpf) < maxSpeed)
                vel + dir * acceleration * tpf
            else
                sign(dir) * maxSpeed

    override fun onUpdate(tpf: Double) {
        currentVelocity = getVelocity(tpf)
        checkBounds(currentVelocity.multiply(tpf))
        entity.translate(currentVelocity.multiply(tpf))
    }

    private fun checkBounds(velocity: Point2D) {
        val upperBound = -PLAYER.size
        val lowerBound = getAppHeight().toDouble()
        val leftBound = -PLAYER.size
        val rightBound = getAppWidth().toDouble()

        if (entity.position.x + velocity.x < leftBound)
            entity.position = Point2D(rightBound, entity.position.y)

        if (entity.position.x + velocity.x > rightBound)
            entity.position = Point2D(leftBound, entity.position.y)

        if (entity.position.y + velocity.y < upperBound)
            entity.position = Point2D(entity.position.x, lowerBound)

        if (entity.position.y + velocity.y > lowerBound)
            entity.position = Point2D(entity.position.x, upperBound)
    }

    override fun isComponentInjectionRequired() = false
}