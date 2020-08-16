package galaxy

import com.almasb.fxgl.animation.Interpolators
import com.almasb.fxgl.app.ApplicationMode
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.core.collection.PropertyChangeListener
import com.almasb.fxgl.dsl.*
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.texture.Texture
import com.almasb.fxgl.texture.merge
import galaxy.collision.LaserBoltToEnemyCollisionHandler
import galaxy.collision.PlayerToEnemyCollisionHandler
import galaxy.collision.PlayerToPowerUpCollisionHandler
import galaxy.components.PlayerMovementComponent
import galaxy.components.PlayerWeaponComponent
import galaxy.entity_data.PLAYER
import galaxy.managers.EnemyManager
import galaxy.managers.PowerUpManager
import javafx.geometry.Point2D
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.text.Text
import javafx.util.Duration
import kotlin.collections.set


class GalaxyApp : GameApplication() {

    private lateinit var player: Entity
    private lateinit var enemyManager: EnemyManager
    private lateinit var powerUpManager: PowerUpManager

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = 800
            height = 600
            title = "Galaxy Shooter"
            version = "1.0-SNAPSHOT"
            applicationMode = ApplicationMode.DEVELOPER
            isDeveloperMenuEnabled = true
            isMainMenuEnabled = true
            sceneFactory = GalaxySceneFactory()
        }
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
        getPhysicsWorld().addCollisionHandler(PlayerToPowerUpCollisionHandler())
    }

    override fun initGame() {
        getGameWorld().addEntityFactory(GalaxyEntityFactory())

        spawnEntityType(GalaxyEntityType.BACKGROUND)
        player = spawnEntityType(GalaxyEntityType.PLAYER)
        initPlayerInput()
        enemyManager = EnemyManager(newLocalTimer())
        powerUpManager = PowerUpManager()
    }

    override fun initGameVars(vars: MutableMap<String, Any>) {
        vars["score"] = 0
        vars["numLives"] = PLAYER.numLives
    }

    override fun initUI() {
        initScoreUI()
        initNumLivesUI()
    }

    private fun initNumLivesUI() {
        val numLivesUI = Texture(image(numberOfLives[geti("numLives")]))
        numLivesUI.x = 10.0
        numLivesUI.y = 10.0
        numLivesUI.fitHeight = 64.0
        numLivesUI.fitWidth = 128.0
        numLivesUI.opacity = .6

        addNumLivesListener(numLivesUI)
        getGameScene().addUINode(numLivesUI)
    }

    private fun addNumLivesListener(numLivesUI: Texture) {
        getGameState().addListener("numLives", object : PropertyChangeListener<Int> {
            override fun onChange(prev: Int, now: Int) {
                numLivesUI.image = image(numberOfLives[geti("numLives")])
            }
        })
    }

    private fun initScoreUI() {
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
            enemyExplosionAnim.size
        )

        animations["playerRollAnim"] = Triple(
            merge(playerRollAnim.map { img -> image(img) }),
            Duration.seconds(1.0),
            playerRollAnim.size
        )

        animations["playerThrusterAnim"] = Triple(
            merge(playerThrusterAnim.map { img -> image(img) }),
            Duration.seconds(3.0),
            playerThrusterAnim.size
        )

        animations["engineOnFireAnim"] = Triple(
            merge(engineOnFireAnim.map { img -> image(img) }),
            Duration.seconds(2.0),
            engineOnFireAnim.size
        )

        animations["numberOfLives"] = Triple(
            merge(numberOfLives.map { img -> image(img) }),
            Duration.seconds(1.0),
            numberOfLives.size
        )

        animations["shieldPowerUpAnim"] = Triple(
            merge(shieldPowerUpAnim.map { img -> image(img) }),
            Duration.seconds(1.0),
            shieldPowerUpAnim.size
        )

        animations["speedPowerUpAnim"] = Triple(
            merge(speedPowerUpAnim.map { img -> image(img) }),
            Duration.seconds(1.0),
            speedPowerUpAnim.size
        )

        animations["tripleShotPowerUpAnim"] = Triple(
            merge(tripleShotPowerUpAnim.map { img -> image(img) }),
            Duration.seconds(1.0),
            tripleShotPowerUpAnim.size
        )

        animations["playerShieldAnim"] = Triple(
            merge(playerShieldAnim.map { img -> image(img) }),
            Duration.seconds(1.0),
            playerShieldAnim.size
        )
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
        powerUpManager.spawnPowerUp(tpf)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GalaxyApp::class.java, args)
        }
    }
}

val animations = HashMap<String, Triple<Image, Duration, Int>>()