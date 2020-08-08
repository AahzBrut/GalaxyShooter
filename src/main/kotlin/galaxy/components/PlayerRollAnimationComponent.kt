package galaxy.components

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.entities.PLAYER
import galaxy.instrumentation.ControllableAnimatedTexture
import galaxy.playerTurnLeftAnim
import galaxy.playerTurnRightAnim
import javafx.scene.image.Image
import javafx.util.Duration
import kotlin.math.sign
import kotlin.math.abs


class PlayerRollAnimationComponent : Component() {

    private lateinit var movementComponent: PlayerMovementComponent
    private lateinit var texture: ControllableAnimatedTexture
    private val textures = mutableListOf<Image>()
    private var rollAngle = 0.0
    private val rollSpeed = PLAYER.rollSpeed

    override fun onAdded() {
        movementComponent = entity.getComponent(PlayerMovementComponent::class.java)
        loadTextures()
        entity.transformComponent.scaleX = PLAYER.size / texture.height
        entity.transformComponent.scaleY = PLAYER.size / texture.height
        entity.viewComponent.addChild(texture)
    }

    private fun loadTextures() {
        textures.addAll(playerTurnLeftAnim.toList().reversed().map { (_, value) -> image(value) })
        textures.addAll(playerTurnRightAnim.toList().drop(1).map { (_, value) -> image(value) })

        val channel = AnimationChannel(textures, Duration.ZERO)
        texture = ControllableAnimatedTexture(channel, ::getRollAngle)
    }

    private fun getRollAngle(tpf: Double) : Int {
        val maxRoll = (textures.size - 1) / 2
        var curDirection = movementComponent.currentDirection.x
        curDirection = if (curDirection == 0.0 && rollAngle != 0.0) -sign(rollAngle) else curDirection

        rollAngle += curDirection * tpf * maxRoll / rollSpeed
        rollAngle = if (abs(rollAngle).toInt() > maxRoll) sign(rollAngle) * maxRoll else rollAngle

        return rollAngle.toInt() + maxRoll
    }

    override fun isComponentInjectionRequired() = false
}