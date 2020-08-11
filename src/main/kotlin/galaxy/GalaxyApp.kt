package galaxy

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import galaxy.GalaxyEntityType.BACKGROUND
import galaxy.GalaxyEntityType.PLAYER
import galaxy.collision.LaserBoltToEnemyCollisionHandler
import galaxy.collision.PlayerToEnemyCollisionHandler
import galaxy.components.PlayerMovementComponent
import galaxy.components.PlayerWeaponComponent
import galaxy.controllers.EnemyController
import javafx.scene.input.KeyCode
import javafx.util.Duration
import kotlin.collections.HashMap
import kotlin.collections.map
import kotlin.collections.set


class GalaxyApp : GameApplication() {

    private lateinit var player: Entity
    private lateinit var enemyController: EnemyController

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

    override fun initPhysics() {
        getPhysicsWorld().addCollisionHandler(LaserBoltToEnemyCollisionHandler())
        getPhysicsWorld().addCollisionHandler(PlayerToEnemyCollisionHandler())
    }

    override fun initGame() {
        getGameWorld().addEntityFactory(GalaxyEntityFactory())

        spawnEntityType(BACKGROUND)
        player = spawnEntityType(PLAYER)
        initPlayerInput()
        enemyController = EnemyController(newLocalTimer())
        initAnimations()
    }

    private fun initAnimations() {

        animations["enemyExplosion"] = AnimatedTexture(AnimationChannel(
                enemyExplosion.map { img -> image(img) },
                Duration.seconds(3.0)))
    }

    private fun initPlayerInput() {
        val input = getInput()
        val playerMovementComponent = player.getComponent(PlayerMovementComponent::class.java)
        val weaponComponent = player.getComponent(PlayerWeaponComponent::class.java)


        input.addAction(playerMovementComponent.forwardThruster, KeyCode.W)
        input.addAction(playerMovementComponent.rightThruster, KeyCode.D)
        input.addAction(playerMovementComponent.leftThruster, KeyCode.A)
        input.addAction(playerMovementComponent.backThruster, KeyCode.S)
        input.addAction(weaponComponent.weaponTrigger, KeyCode.SPACE)
    }

    override fun onUpdate(tpf: Double) {
        enemyController.spawnEnemies(tpf)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GalaxyApp::class.java, args)
        }
    }
}

val animations = HashMap<String, AnimatedTexture>()