package galaxy.collision

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import galaxy.GalaxyEntityType
import galaxy.components.HealthComponent
import galaxy.entity_data.ENEMY

class PlayerToEnemyCollisionHandler: CollisionHandler(
        GalaxyEntityType.PLAYER,
        GalaxyEntityType.ENEMY) {

    override fun onCollisionBegin(player: Entity, enemy: Entity) {

        player.getComponent(HealthComponent::class.java).takeDamage(ENEMY.maxHealth)
        enemy.getComponent(HealthComponent::class.java).takeDamage(ENEMY.maxHealth)
    }
}