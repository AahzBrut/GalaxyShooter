package galaxy.components

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.animations

class PlayerThrusterAnimationComponent : Component() {

    override fun onAdded() {
        val channel = AnimationChannel(animations["playerThrusterAnim"]!!.first, animations["playerThrusterAnim"]!!.second, animations["playerThrusterAnim"]!!.third)
        val texture = AnimatedTexture(channel)
        texture.translateY = 45.0 / entity.transformComponent.scaleY
        entity.viewComponent.addChild(texture)
        texture.loop()
    }

    override fun isComponentInjectionRequired() = false
}