package galaxy.components

import com.almasb.fxgl.entity.component.Component
import galaxy.DOWN
import galaxy.LEFT
import galaxy.RIGHT
import galaxy.UP
import javafx.geometry.Point2D
import kotlin.math.abs
import kotlin.math.sign

class PlayerMovementComponent : Component() {

    private val maxSpeed: Double = 10.0
    private val acceleration: Double = 10.0

    private var currentDirection: Point2D = Point2D.ZERO
    private var currentVelocity: Point2D = Point2D.ZERO

    private fun getVelocity(tpf: Double): Point2D {

        val dx = if (currentDirection.x == 0.0) {
            getDecelVelocity(currentVelocity.x, tpf)
        } else {
            getAccelVelocity(currentVelocity.x, tpf)
        }
        println(dx)
        return Point2D(dx, 0.0)
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

    private fun getAccelVelocity(vel: Double, tpf: Double) =
            if (abs(vel + currentDirection.x * acceleration * tpf) < maxSpeed)
                vel + currentDirection.x * acceleration * tpf
            else
                sign(currentDirection.x) * maxSpeed

    override fun onUpdate(tpf: Double) {

        val velocity = getVelocity(tpf)
        currentVelocity = velocity
        getEntity().translate(velocity)
    }

    fun moveLeft() {
        currentDirection = Point2D(LEFT.x, currentDirection.y)
    }

    fun stopMoveLeft() {
        currentDirection = Point2D(0.0, currentDirection.y)
    }

    fun moveRight() {
        currentDirection = Point2D(RIGHT.x, currentDirection.y)
    }

    fun stopMoveRight() {
        currentDirection = Point2D(0.0, currentDirection.y)
    }

    fun moveUp() {
        println("Start moving up")
        currentDirection = Point2D(currentDirection.x, UP.y)
    }

    fun stopMoveUp() {
        println("Stop moving up")
        currentDirection = Point2D(currentDirection.x, 0.0)
    }

    fun moveDown() {
        currentDirection = Point2D(currentDirection.x, DOWN.y)
    }

    fun stopMoveDown() {
        currentDirection = Point2D(currentDirection.x, DOWN.y)
    }
}