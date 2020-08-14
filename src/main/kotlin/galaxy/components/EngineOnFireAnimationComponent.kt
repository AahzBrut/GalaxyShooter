package galaxy.components

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.animations
import galaxy.entity_data.PLAYER

class EngineOnFireAnimationComponent : Component() {

    fun takeDamage(remainingHealth: Int) {
        val dx = if (remainingHealth == 2) -PLAYER.size/ 3 else PLAYER.size/ 3
        val dy = 3 * PLAYER.size / 4
        val animation = animations["engineOnFireAnim"]

        val animatedTexture = AnimatedTexture(AnimationChannel(animation!!.first, animation.second, animation.third))
        animatedTexture.translateY = dy / entity.transformComponent.scaleY
        animatedTexture.translateX = dx / entity.transformComponent.scaleY

        entity.viewComponent.addChild(animatedTexture)
        animatedTexture.loop()
    }

    override fun isComponentInjectionRequired() = false
}