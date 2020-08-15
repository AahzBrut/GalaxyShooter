package galaxy.components

import com.almasb.fxgl.dsl.getGameState
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.component.Component
import galaxy.GalaxyEntityType
import galaxy.animations
import galaxy.entity_data.ENEMY
import galaxy.entity_data.PLAYER
import galaxy.score

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
        if (entity.type == GalaxyEntityType.PLAYER) {
            entity.getComponent(EngineOnFireAnimationComponent::class.java).takeDamage(health)
            getGameState().setValue("numLives", health)
        }
    }

    private fun killEntity() {
        if (entity.type == GalaxyEntityType.ENEMY) {
            spawn("Explosion", SpawnData(entity.transformComponent.position)
                    .put("animation", animations["enemyExplosion"]!!))
            score(ENEMY.scoreForKill)
        }
        if (entity.type == GalaxyEntityType.PLAYER) {
            getGameState().setValue("numLives", health)
        }
        entity.removeFromWorld()
    }

    override fun isComponentInjectionRequired() = false
}