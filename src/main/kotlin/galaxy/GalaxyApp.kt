package galaxy

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.getInput
import com.almasb.fxgl.dsl.getSettings
import com.almasb.fxgl.dsl.loopBGM
import com.almasb.fxgl.entity.Entity
import galaxy.GalaxyEntityType.BACKGROUND
import galaxy.GalaxyEntityType.PLAYER
import galaxy.components.PlayerMovementComponent
import galaxy.components.PlayerWeaponComponent
import javafx.scene.input.KeyCode

class GalaxyApp : GameApplication() {

    private lateinit var player: Entity

    override fun initSettings(settings: GameSettings) {
        settings.width = 800
        settings.height = 600
        settings.title = "Galaxy Shooter"
        settings.version = "1.0-SNAPSHOT"
    }

    override fun onPreInit() {
        getSettings().globalSoundVolume = 0.2
        getSettings().globalMusicVolume = 0.5

        loopBGM("music_background.wav")
    }

    override fun initGame() {
        getGameWorld().addEntityFactory(GalaxyEntityFactory())

        spawnEntityType(BACKGROUND)
        player = spawnEntityType(PLAYER)

        initPlayerInput()
    }

    private fun initPlayerInput() {
        val input = getInput()
        val playerMovementComponent = player.getComponent(PlayerMovementComponent::class.java)
        val weaponComponent = player.getComponent(PlayerWeaponComponent::class.java)


        input.addAction(playerMovementComponent.forwardThruster, KeyCode.W)
        input.addAction(playerMovementComponent.rightThruster, KeyCode.D)
        input.addAction(playerMovementComponent.leftThruster, KeyCode.A)
        input.addAction(playerMovementComponent.backThruster, KeyCode.S)
        input.addAction(weaponComponent.weaponPylon, KeyCode.SPACE)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GalaxyApp::class.java, args)
        }
    }
}