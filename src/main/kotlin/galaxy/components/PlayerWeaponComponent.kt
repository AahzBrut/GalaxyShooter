package galaxy.components

import com.almasb.fxgl.dsl.newLocalTimer
import com.almasb.fxgl.dsl.play
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.input.UserAction
import galaxy.entities.LASER_BOLT
import galaxy.entities.PLAYER
import javafx.util.Duration

class PlayerWeaponComponent : Component() {

    val weaponTrigger = object : UserAction("WeaponTrigger") {
        override fun onAction() = run { shoot() }
    }

    private val cooldownTimer = newLocalTimer()

    private fun shoot(){
        if (!cooldownTimer.elapsed(Duration.seconds(PLAYER.firingDelay))) return
        cooldownTimer.capture()

        play(LASER_BOLT.shotSound)
        spawn("LaserBolt", entity.transformComponent.position.add(PLAYER.mainWeaponPos))
    }

    override fun isComponentInjectionRequired() = false
}