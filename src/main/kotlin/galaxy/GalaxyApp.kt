package galaxy

import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.getGameScene
import com.almasb.fxgl.dsl.getGameWorld
import com.almasb.fxgl.dsl.spawn
import javafx.scene.paint.Color

class GalaxyApp : GameApplication(){

    override fun initSettings(settings: GameSettings) {
        settings.width = 800
        settings.height = 600
        settings.title = "Galaxy Shooter"
        settings.version = "1.0-SNAPSHOT"
    }

    override fun initGame() {
        getGameWorld().addEntityFactory(GalaxyEntityFactory())
        getGameScene().setBackgroundColor(Color.BLACK)

        spawn("Background")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GalaxyApp::class.java, args)
        }
    }
}