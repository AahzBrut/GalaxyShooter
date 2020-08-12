package galaxy.components

import com.almasb.fxgl.dsl.play
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.entity_data.ENEMY
import javafx.scene.image.Image
import javafx.util.Duration

class ExplosionAnimationComponent(private val animation: Triple<Image, Duration, Int>): Component() {

    override fun onAdded() {
        val animatedTexture = AnimatedTexture(AnimationChannel(animation.first, animation.second, animation.third))
        entity.transformComponent.scaleX = ENEMY.size / animatedTexture.height
        entity.transformComponent.scaleY = ENEMY.size / animatedTexture.height
        entity.viewComponent.addChild(animatedTexture)
        animatedTexture.onCycleFinished = Runnable { explosionEnds() }
        animatedTexture.play()
        play(ENEMY.explosionSound)
    }

    private fun explosionEnds() {
        entity.removeFromWorld()
    }

    override fun isComponentInjectionRequired() = false
}