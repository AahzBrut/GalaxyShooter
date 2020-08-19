package galaxy.collision

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import galaxy.GalaxyEntityType
import galaxy.components.PowerUpAnimationComponent
import galaxy.components.PowerUpReceiverComponent

class PlayerToPowerUpCollisionHandler: CollisionHandler(
    GalaxyEntityType.PLAYER,
    GalaxyEntityType.POWER_UP
) {
    override fun onCollisionBegin(player: Entity, powerUp: Entity) {

        val powerUpType = powerUp.getComponent(PowerUpAnimationComponent::class.java).powerUpType
        player.getComponent(PowerUpReceiverComponent::class.java).activate(powerUpType)
        powerUp.removeFromWorld()
    }
}