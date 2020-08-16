package galaxy.components

import com.almasb.fxgl.dsl.runOnce
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.PowerUpType
import galaxy.animations
import javafx.util.Duration

class PlayerShieldAnimComponent: Component() {

    private lateinit var animatedTexture: AnimatedTexture

    override fun onAdded() {
        addAnimatedTexture()
    }

    private fun addAnimatedTexture() {
        val animation = animations["playerShieldAnim"]
        animatedTexture = AnimatedTexture(AnimationChannel(animation!!.first, animation.second, animation.third))
        animatedTexture.scaleX = 2.0
        animatedTexture.scaleY = 2.0
        animatedTexture.opacity = .4
        entity.viewComponent.addChild(animatedTexture)
        animatedTexture.loop()
    }

    override fun onUpdate(tpf: Double) {
        if (!entity.getComponent(PowerUpReceiverComponent::class.java).isActive(PowerUpType.SHIELD)) {
            runOnce(this::removeShield, Duration.ZERO)
        }
    }

    private fun removeShield() {
        entity.removeComponent(PlayerShieldAnimComponent::class.java)
    }

    override fun onRemoved() {
        entity.viewComponent.removeChild(animatedTexture)
    }

    override fun isComponentInjectionRequired() = false
}