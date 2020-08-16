package galaxy.components

import com.almasb.fxgl.dsl.newLocalTimer
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.time.LocalTimer
import galaxy.PowerUpType

class PowerUpReceiverComponent : Component() {

    private val active = HashMap<PowerUpType, LocalTimer>()

    fun activate(powerUpType: PowerUpType) {
        active.replace(powerUpType, newLocalTimer())
    }

    fun isActive(powerUpType: PowerUpType): Boolean =
        active[powerUpType]?.elapsed(powerUpType.activeTime)?.not() ?: false

    override fun isComponentInjectionRequired() = false
}