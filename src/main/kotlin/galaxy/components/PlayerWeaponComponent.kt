package galaxy.components

import com.almasb.fxgl.dsl.play
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.input.UserAction
import galaxy.K
import galaxy.LASER_SHOT
import galaxy.entities.PLAYER

class PlayerWeaponComponent : Component() {

    val weaponTrigger = object : UserAction("WeaponTrigger") {
        override fun onAction() = run { shoot() }
    }

    private var lastShotFired: Long = System.currentTimeMillis()

    private fun shoot(){
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastShotFired <= PLAYER.firingDelay * K) return

        lastShotFired = currentTime
        play(LASER_SHOT)
        spawn("LaserBolt", entity.transformComponent.position.add(PLAYER.mainWeaponPos))
    }

    override fun isComponentInjectionRequired() = false
}