package galaxy.components

import com.almasb.fxgl.dsl.newLocalTimer
import com.almasb.fxgl.dsl.play
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.input.UserAction
import galaxy.entity_data.LASER_BOLT
import galaxy.entity_data.PLAYER
import javafx.util.Duration

class PlayerWeaponComponent : Component() {

    val weaponTrigger = object : UserAction("WeaponTrigger") {
        override fun onAction() = run { shoot() }
    }

    private val coolDownTimer = newLocalTimer()

    private fun shoot(){
        if (entity == null || !coolDownTimer.elapsed(Duration.seconds(PLAYER.firingDelay))) return
        coolDownTimer.capture()

        play(LASER_BOLT.shotSound)
        spawn("LaserBolt", entity.transformComponent.position.add(PLAYER.mainWeaponPos))
    }

    override fun isComponentInjectionRequired() = false
}