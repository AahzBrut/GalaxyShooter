package galaxy.components

import com.almasb.fxgl.dsl.getAppHeight
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.PowerUpType
import galaxy.animations
import galaxy.entity_data.POWER_UP

class PowerUpAnimationComponent(private val powerUpType: PowerUpType): Component() {

    private val speed = POWER_UP.speed

    override fun onAdded() {
        val animation = animations[powerUpType.animationName]
        val animatedTexture = AnimatedTexture(AnimationChannel(animation!!.first, animation.second, animation.third))
        entity.transformComponent.scaleX = POWER_UP.size / animatedTexture.height
        entity.transformComponent.scaleY = POWER_UP.size / animatedTexture.height
        entity.viewComponent.addChild(animatedTexture)
        animatedTexture.loop()
    }

    override fun onUpdate(tpf: Double) {
        entity.translate(0.0, speed * tpf)
        if (entity.position.y > getAppHeight())
            entity.removeFromWorld()
    }

    override fun isComponentInjectionRequired() = false
}