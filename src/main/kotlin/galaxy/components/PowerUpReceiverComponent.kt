package galaxy.components

import com.almasb.fxgl.dsl.newLocalTimer
import com.almasb.fxgl.dsl.play
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.time.LocalTimer
import galaxy.PowerUpType
import galaxy.entity_data.POWER_UP

class PowerUpReceiverComponent : Component() {

    private val active = HashMap<PowerUpType, LocalTimer>()

    fun activate(powerUpType: PowerUpType) {
        active.putIfAbsent(powerUpType, newLocalTimer())
        active[powerUpType]?.capture()

        addShieldAnimation(powerUpType)
        play(POWER_UP.activateSound)
    }

    private fun addShieldAnimation(powerUpType: PowerUpType) {
        if (powerUpType == PowerUpType.SHIELD) {
            if (entity.hasComponent(PlayerShieldAnimComponent::class.java)) {
                entity.removeComponent(PlayerShieldAnimComponent::class.java)
            }
            entity.addComponent(PlayerShieldAnimComponent())
        }
    }

    fun isActive(powerUpType: PowerUpType): Boolean {

        return active[powerUpType]?.elapsed(powerUpType.activeTime)?.not() ?: false
    }

    override fun isComponentInjectionRequired() = false
}