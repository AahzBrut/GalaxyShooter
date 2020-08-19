package galaxy

import com.almasb.fxgl.app.ApplicationMode
import com.almasb.fxgl.animation.Interpolators
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.core.collection.PropertyChangeListener
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.input.UserAction
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
        with(settings) {
            width = 800
            height = 600
            title = "Galaxy Shooter"
            version = "1.0-SNAPSHOT"
            applicationMode = ApplicationMode.DEVELOPER
            isMainMenuEnabled = true
            sceneFactory = GalaxySceneFactory()
        }
    }

    override fun initInput() {
        getInput().addAction(object : UserAction("ForwardThruster") {
            override fun onActionBegin() = run { player.getComponent(PlayerMovementComponent::class.java).forwardThrusterBegin() }
            override fun onActionEnd() = run { player.getComponent(PlayerMovementComponent::class.java).forwardThrusterEnd() }
        }, KeyCode.W)
        getInput().addAction(object : UserAction("BackThruster") {
            override fun onActionBegin() = run { player.getComponent(PlayerMovementComponent::class.java).backThrusterBegin() }
            override fun onActionEnd() = run { player.getComponent(PlayerMovementComponent::class.java).backThrusterEnd() }
        }, KeyCode.S)
        getInput().addAction(object : UserAction("RightThruster") {
            override fun onActionBegin() = run { player.getComponent(PlayerMovementComponent::class.java).rightThrusterBegin() }
            override fun onActionEnd() = run { player.getComponent(PlayerMovementComponent::class.java).rightThrusterEnd() }
        }, KeyCode.D)
        getInput().addAction(object : UserAction("LeftThruster") {
            override fun onActionBegin() = run { player.getComponent(PlayerMovementComponent::class.java).leftThrusterBegin() }
            override fun onActionEnd() = run { player.getComponent(PlayerMovementComponent::class.java).leftThrusterEnd() }
        }, KeyCode.A)
        getInput().addAction(object : UserAction("WeaponTrigger") {
            override fun onAction() = run { player.getComponent(PlayerWeaponComponent::class.java).shoot() }
        }, KeyCode.SPACE)
    }

    override fun onPreInit() {
        getSettings().globalSoundVolume = 0.2
        getSettings().globalMusicVolume = 0.5

        loopBGM("music_background.wav")
        initAnimations()
    }

    override fun initGameVars(vars: MutableMap<String, Any>) {
        vars["score"] = 0
    }

    override fun initPhysics() {
        getPhysicsWorld().addCollisionHandler(LaserBoltToEnemyCollisionHandler())
        getPhysicsWorld().addCollisionHandler(PlayerToEnemyCollisionHandler())
    }

    override fun initGame() {
        getGameWorld().addEntityFactory(GalaxyEntityFactory())

        spawnEntityType(BACKGROUND)
        player = spawnEntityType(PLAYER)
        enemyManager = EnemyManager(newLocalTimer())
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

    override fun onUpdate(tpf: Double) {
        enemyManager.spawnEnemies(tpf)
        if (!player.isActive) gameOver()
    }

    private fun gameOver(){
        getDisplay().showMessageBox("Game over. Press OK to exit", ({ getGameController().gotoMainMenu()}))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GalaxyApp::class.java, args)
        }
    }

}

val animations = HashMap<String, Triple<Image, Duration, Int>>()