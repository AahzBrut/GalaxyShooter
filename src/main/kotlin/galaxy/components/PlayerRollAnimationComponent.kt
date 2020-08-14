package galaxy.components

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimationChannel
import com.almasb.fxgl.texture.Texture
import galaxy.animations
import galaxy.entity_data.PLAYER
import galaxy.instrumentation.ControllableAnimatedTexture
import kotlin.math.abs
import kotlin.math.sign


class PlayerRollAnimationComponent : Component() {

    private lateinit var movementComponent: PlayerMovementComponent
    private var rollAngle = 0.0
    private val rollSpeed = PLAYER.rollSpeed

    override fun onAdded() {
        movementComponent = entity.getComponent(PlayerMovementComponent::class.java)
        val texture = loadTextures()
        entity.transformComponent.scaleX = PLAYER.size / texture.height
        entity.transformComponent.scaleY = PLAYER.size / texture.height
        entity.viewComponent.addChild(texture)
    }

    private fun loadTextures() : Texture {

        val channel = AnimationChannel(animations["playerRollAnim"]!!.first, animations["playerRollAnim"]!!.second, animations["playerRollAnim"]!!.third)
        return ControllableAnimatedTexture(channel, ::getRollAngle)
    }

    private fun getRollAngle(tpf: Double) : Int {
        val maxRoll = (animations["playerRollAnim"]!!.third - 1) / 2
        var curDirection = movementComponent.currentDirection.x
        curDirection = if (curDirection == 0.0 && rollAngle != 0.0) -sign(rollAngle) else curDirection

        rollAngle += curDirection * tpf * maxRoll / rollSpeed
        rollAngle = if (abs(rollAngle).toInt() > maxRoll) sign(rollAngle) * maxRoll else rollAngle

        return rollAngle.toInt() + maxRoll
    }

    override fun isComponentInjectionRequired() = false
}