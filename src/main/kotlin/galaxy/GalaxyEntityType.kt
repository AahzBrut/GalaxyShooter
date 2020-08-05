package galaxy

import com.almasb.fxgl.dsl.getAppHeight
import com.almasb.fxgl.dsl.getAppWidth
import javafx.geometry.Point2D

enum class GalaxyEntityType(
        val typeName: String,
        val baseTexture: String,
        val spawnPosition: Point2D
) {
    BACKGROUND(
            "Background",
            "background/SpaceBG_Overlay.png",
            Point2D(0.0,0.0)

    ),
    PLAYER(
            "Player",
            "player/player.png",
            Point2D(getAppWidth().toDouble() / 2, getAppHeight().toDouble() / 2)
    )
}