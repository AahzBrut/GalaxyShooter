package galaxy.components

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.input.UserAction
import galaxy.DOWN
import galaxy.LEFT
import galaxy.RIGHT
import galaxy.UP
import javafx.geometry.Point2D
import kotlin.math.abs
import kotlin.math.sign

class PlayerMovementComponent : Component() {
    val forwardThruster = object : UserAction("ForwardThruster") {
        override fun onActionBegin() = run { currentDirection = Point2D(currentDirection.x, UP.y) }
        override fun onActionEnd() = run { currentDirection = Point2D(currentDirection.x, 0.0) }
    }
    val rightThruster = object : UserAction("RightThruster") {
        override fun onActionBegin() = run { currentDirection = Point2D(RIGHT.x, currentDirection.y) }
        override fun onActionEnd() = run { currentDirection = Point2D(0.0, currentDirection.y) }
    }
    val leftThruster = object : UserAction("LeftThruster") {
        override fun onActionBegin() = run { currentDirection = Point2D(LEFT.x, currentDirection.y) }
        override fun onActionEnd() = run { currentDirection = Point2D(0.0, currentDirection.y) }
    }
    val backThruster = object : UserAction("BackThruster") {
        override fun onActionBegin() = run { currentDirection = Point2D(currentDirection.x, DOWN.y) }
        override fun onActionEnd() = run { currentDirection = Point2D(currentDirection.x, 0.0) }
    }

    private val maxSpeed: Double = 10.0
    private val acceleration: Double = 10.0

    private var currentDirection: Point2D = Point2D.ZERO
    private var currentVelocity: Point2D = Point2D.ZERO

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
        val velocity = getVelocity(tpf)
        currentVelocity = velocity

        getEntity().translate(velocity)
    }
}