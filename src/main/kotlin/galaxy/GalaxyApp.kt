package galaxy

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.getInput
import com.almasb.fxgl.dsl.getSettings
import com.almasb.fxgl.dsl.loopBGM
import com.almasb.fxgl.dsl.onKeyDown
import com.almasb.fxgl.dsl.onKeyUp
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.input.UserAction
import galaxy.GalaxyEntityType.BACKGROUND
import galaxy.GalaxyEntityType.PLAYER
import galaxy.components.PlayerMovementComponent
import javafx.scene.input.KeyCode

class GalaxyApp : GameApplication() {

    private lateinit var player: Entity
    private lateinit var playerMovementComponent: PlayerMovementComponent

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
        playerMovementComponent = player.getComponent(PlayerMovementComponent::class.java)
    }

    override fun initInput() {
        val input = getInput()

        val forwardThruster = object: UserAction("ForwardThruster") {
            override fun onActionBegin() = playerMovementComponent.moveUp()
            override fun onActionEnd() = playerMovementComponent.stopMoveUp()
        }

        val rightThruster = object: UserAction("RightThruster") {
            override fun onActionBegin() = playerMovementComponent.moveRight()
            override fun onActionEnd() = playerMovementComponent.stopMoveRight()
        }

        val leftThruster = object: UserAction("LeftThruster") {
            override fun onActionBegin() = playerMovementComponent.moveLeft()
            override fun onActionEnd() = playerMovementComponent.stopMoveLeft()
        }

        input.addAction(forwardThruster, KeyCode.W)
        input.addAction(rightThruster, KeyCode.D)
        input.addAction(leftThruster, KeyCode.A)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GalaxyApp::class.java, args)
        }
    }
}