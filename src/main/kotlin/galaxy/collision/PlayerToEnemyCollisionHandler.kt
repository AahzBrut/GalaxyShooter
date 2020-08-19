package galaxy.collision

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import galaxy.GalaxyEntityType
import galaxy.PowerUpType
import galaxy.components.HealthComponent
import galaxy.components.PowerUpReceiverComponent
import galaxy.entity_data.ENEMY

class PlayerToEnemyCollisionHandler : CollisionHandler(
    GalaxyEntityType.PLAYER,
    GalaxyEntityType.ENEMY
) {

    override fun onCollisionBegin(player: Entity, enemy: Entity) {

        if (!player.getComponent(PowerUpReceiverComponent::class.java).isActive(PowerUpType.SHIELD))
            player.getComponent(HealthComponent::class.java).takeDamage(ENEMY.maxHealth)
        enemy.getComponent(HealthComponent::class.java).takeDamage(ENEMY.maxHealth)
    }
}