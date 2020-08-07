package galaxy

import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.SpawnData
import javafx.geometry.Point2D


fun spawnEntityType(entityType: GalaxyEntityType)  = spawn(entityType.typeName, SpawnData(entityType.spawnPosition).put("baseTexture", entityType.baseTexture))


val LEFT = Point2D(-1.0, 0.0)
val RIGHT = Point2D(1.0, 0.0)
val UP = Point2D(0.0, -1.0)
val DOWN = Point2D(0.0, 1.0)
const val K : Long = 1_000


val playerTurnLeftAnim = mapOf(
        0 to "player/turn-left/Player Turn Left0000.png",
        1 to "player/turn-left/Player Turn Left0001.png",
        2 to "player/turn-left/Player Turn Left0002.png",
        3 to "player/turn-left/Player Turn Left0003.png",
        4 to "player/turn-left/Player Turn Left0004.png",
        5 to "player/turn-left/Player Turn Left0005.png",
        6 to "player/turn-left/Player Turn Left0006.png",
        7 to "player/turn-left/Player Turn Left0007.png",
        8 to "player/turn-left/Player Turn Left0008.png")

val playerTurnRightAnim = mapOf(
        0 to "player/turn-right/Player Turn Right0000.png",
        1 to "player/turn-right/Player Turn Right0001.png",
        2 to "player/turn-right/Player Turn Right0002.png",
        3 to "player/turn-right/Player Turn Right0003.png",
        4 to "player/turn-right/Player Turn Right0004.png",
        5 to "player/turn-right/Player Turn Right0005.png",
        6 to "player/turn-right/Player Turn Right0006.png",
        7 to "player/turn-right/Player Turn Right0007.png",
        8 to "player/turn-right/Player Turn Right0008.png")

const val LASER_SHOT = "laser_shot.wav"
