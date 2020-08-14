package galaxy.components

import com.almasb.fxgl.dsl.play
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.entity_data.ENEMY
import javafx.scene.image.Image
import javafx.util.Duration
import kotlin.properties.Delegates

class ExplosionAnimationComponent(private val animation: Triple<Image, Duration, Int>): Component() {

    private var speed = 0.0

    override fun onAdded() {
        speed = ENEMY.speed
        val animatedTexture = AnimatedTexture(AnimationChannel(animation.first, animation.second, animation.third))
        entity.transformComponent.scaleX = ENEMY.size / animatedTexture.height
        entity.transformComponent.scaleY = ENEMY.size / animatedTexture.height
        entity.viewComponent.addChild(animatedTexture)
        animatedTexture.onCycleFinished = Runnable { explosionEnds() }
        animatedTexture.play()
        play(ENEMY.explosionSound)
    }

    override fun onUpdate(tpf: Double) {
        entity.translate(0.0, speed * tpf)
        speed *= ENEMY.explosionDrag
    }

    private fun explosionEnds() {
        entity.removeFromWorld()
    }

    override fun isComponentInjectionRequired() = false
}