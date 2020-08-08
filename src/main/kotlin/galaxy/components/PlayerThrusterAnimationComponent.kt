package galaxy.components

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.playerThrusterAnim
import javafx.scene.image.Image
import javafx.util.Duration

class PlayerThrusterAnimationComponent : Component() {

    private val images = mutableListOf<Image>()
    private lateinit var movementComponent: PlayerMovementComponent
    private lateinit var texture: AnimatedTexture


    override fun onAdded() {
        movementComponent = entity.getComponent(PlayerMovementComponent::class.java)
        images.addAll(playerThrusterAnim.map{ item -> image(item) })
        val channel = AnimationChannel(images, Duration.seconds(1.0))
        texture = AnimatedTexture(channel)
        texture.translateY = 45.0 / entity.transformComponent.scaleY
        entity.viewComponent.addChild(texture)
        texture.loop()
    }

    override fun isComponentInjectionRequired() = false
}