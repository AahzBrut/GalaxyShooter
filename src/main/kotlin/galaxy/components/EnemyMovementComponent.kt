package galaxy.components

import com.almasb.fxgl.dsl.getAppHeight
import com.almasb.fxgl.entity.component.Component
import galaxy.entity_data.ENEMY

class EnemyMovementComponent : Component() {
    private val speed = ENEMY.speed

    override fun onUpdate(tpf: Double) {
        if (entity.position.y > getAppHeight())
            entity.removeFromWorld()
        else
            entity.translate(0.0, speed * tpf)
    }

    override fun isComponentInjectionRequired() = false
}