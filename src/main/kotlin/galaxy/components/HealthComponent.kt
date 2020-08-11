package galaxy.components

import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.component.Component
import galaxy.GalaxyEntityType
import galaxy.animations

class HealthComponent(maxHealthPoints: Int) : Component() {

    private var health = maxHealthPoints

    fun takeDamage(damage: Int){
        health -= damage

        if (health <= 0) {
            killEntity()
        } else {
            injureEntity(health)
        }
    }

    private fun injureEntity(health: Int) {
        println("player got damaged, remaining health: $health")
    }

    private fun killEntity() {
        if (entity.type == GalaxyEntityType.ENEMY)
            spawn("Explosion", SpawnData(entity.transformComponent.position)
                    .put("animation", animations["enemyExplosion"] ?: error("Enemy explosion animation was not initialized"))
            )
        entity.removeFromWorld()
    }

    override fun isComponentInjectionRequired() = false
}