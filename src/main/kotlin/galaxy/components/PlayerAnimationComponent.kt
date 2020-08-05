package galaxy.components

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.playerTurnLeftAnim
import galaxy.playerTurnRightAnim
import javafx.util.Duration


class PlayerAnimationComponent : Component() {

    private lateinit var movementComponent: PlayerMovementComponent
    private lateinit var texture: AnimatedTexture
    private lateinit var animIdle: AnimationChannel
    private lateinit var animTurnLeft: AnimationChannel
    private lateinit var animTurnRight: AnimationChannel

    override fun onAdded() {
        movementComponent = entity.getComponent(PlayerMovementComponent::class.java)
        loadTextures()
        entity.transformComponent.scaleX = 60.0 / texture.height
        entity.transformComponent.scaleY = 60.0 / texture.width
        entity.viewComponent.addChild(texture)
    }

    private fun loadTextures() {
        animIdle = AnimationChannel(playerTurnLeftAnim.toList().dropLast(playerTurnLeftAnim.size - 1).map { (_, value) -> image(value) }.toList(), Duration.seconds(.1))
        animTurnLeft = AnimationChannel(playerTurnLeftAnim.map { (_, value) -> image(value) }.toList(), Duration.seconds(1.0))
        animTurnRight = AnimationChannel(playerTurnRightAnim.map { (_, value) -> image(value) }.toList(), Duration.seconds(1.0))


        texture = AnimatedTexture(animIdle)
    }

    override fun isComponentInjectionRequired() = false
}