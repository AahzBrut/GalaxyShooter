package galaxy

import com.almasb.fxgl.animation.Interpolators
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.core.collection.PropertyChangeListener
import com.almasb.fxgl.dsl.addUINode
import com.almasb.fxgl.dsl.animationBuilder
import com.almasb.fxgl.dsl.getAppWidth
import com.almasb.fxgl.dsl.getGameState
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.getInput
import com.almasb.fxgl.dsl.getPhysicsWorld
import com.almasb.fxgl.dsl.getSettings
import com.almasb.fxgl.dsl.getUIFactoryService
import com.almasb.fxgl.dsl.getip
import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.dsl.loopBGM
import com.almasb.fxgl.dsl.newLocalTimer
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.texture.merge
import galaxy.GalaxyEntityType.BACKGROUND
import galaxy.GalaxyEntityType.PLAYER
import galaxy.collision.LaserBoltToEnemyCollisionHandler
import galaxy.collision.PlayerToEnemyCollisionHandler
import galaxy.components.PlayerMovementComponent
import galaxy.components.PlayerWeaponComponent
import galaxy.controllers.EnemyManager
import javafx.geometry.Point2D
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.text.Text
import javafx.util.Duration
import kotlin.collections.set


class GalaxyApp : GameApplication() {

    private lateinit var player: Entity
    private lateinit var enemyManager: EnemyManager

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
        initAnimations()
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
        enemyManager = EnemyManager(newLocalTimer())
    }

    override fun initGameVars(vars: MutableMap<String, Any>) {
        vars["score"] = 0
    }

    override fun initUI() {
        val text = getUIFactoryService().newText("", 24.0)
        text.textProperty().bind(getip("score").asString("Score:%d"))
        addScoreListener(text)
        addUINode(text, (getAppWidth() - 200).toDouble(), 40.0)
    }

    private fun addScoreListener(text: Text) {

        getGameState().addListener("score", object : PropertyChangeListener<Int> {
            override fun onChange(prev: Int, now: Int) {
                animationBuilder()
                        .duration(Duration.seconds(0.5))
                        .interpolator(Interpolators.BOUNCE.EASE_OUT())
                        .repeat(2)
                        .autoReverse(true)
                        .scale(text)
                        .from(Point2D(1.0, 1.0))
                        .to(Point2D(1.2, 1.2))
                        .buildAndPlay()
            }
        })
    }

    private fun initAnimations() {

        animations["enemyExplosion"] = Triple(
                merge(enemyExplosionAnim.map { img -> image(img) }),
                Duration.seconds(3.0),
                enemyExplosionAnim.size)

        animations["playerRollAnim"] = Triple(
                merge(playerRollAnim.map { img -> image(img) }),
                Duration.seconds(1.0),
                playerRollAnim.size)

        animations["playerThrusterAnim"] = Triple(
                merge(playerThrusterAnim.map { img -> image(img) }),
                Duration.seconds(3.0),
                playerThrusterAnim.size)

        animations["engineOnFireAnim"] = Triple(
                merge(engineOnFireAnim.map { img -> image(img) }),
                Duration.seconds(2.0),
                engineOnFireAnim.size)
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
        enemyManager.spawnEnemies(tpf)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GalaxyApp::class.java, args)
        }
    }
}

val animations = HashMap<String, Triple<Image, Duration, Int>>()