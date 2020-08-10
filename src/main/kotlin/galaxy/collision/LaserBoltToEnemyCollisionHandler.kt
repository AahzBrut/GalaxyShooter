package galaxy.collision

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import galaxy.GalaxyEntityType
import galaxy.components.HealthComponent
import galaxy.entity_data.LASER_BOLT

class LaserBoltToEnemyCollisionHandler : CollisionHandler(
        GalaxyEntityType.LASER_BOLT,
        GalaxyEntityType.ENEMY) {

    override fun onCollisionBegin(laserBolt: Entity, enemy: Entity) {

        laserBolt.getComponent(HealthComponent::class.java).takeDamage(LASER_BOLT.maxHealth)
        enemy.getComponent(HealthComponent::class.java).takeDamage(LASER_BOLT.damage)
    }
}