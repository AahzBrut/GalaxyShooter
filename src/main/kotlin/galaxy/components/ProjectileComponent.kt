package galaxy.components

import com.almasb.fxgl.entity.component.Component
import galaxy.entities.LASER_BOLT

class ProjectileComponent : Component() {

    private val speed = LASER_BOLT.speed

    override fun onUpdate(tpf: Double) {
        if (entity.position.y < - 30)
            entity.removeFromWorld()

        entity.translate(0.0, -speed * tpf)
    }

    override fun isComponentInjectionRequired() = false
}