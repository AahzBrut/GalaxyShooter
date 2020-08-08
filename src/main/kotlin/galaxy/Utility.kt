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

val playerThrusterAnim = listOf(
        "player/thruster/Thruster_00000.png",
        "player/thruster/Thruster_00001.png",
        "player/thruster/Thruster_00002.png",
        "player/thruster/Thruster_00003.png",
        "player/thruster/Thruster_00004.png",
        "player/thruster/Thruster_00005.png",
        "player/thruster/Thruster_00006.png",
        "player/thruster/Thruster_00007.png",
        "player/thruster/Thruster_00008.png",
        "player/thruster/Thruster_00009.png",
        "player/thruster/Thruster_00010.png",
        "player/thruster/Thruster_00011.png",
        "player/thruster/Thruster_00012.png",
        "player/thruster/Thruster_00013.png",
        "player/thruster/Thruster_00014.png",
        "player/thruster/Thruster_00015.png",
        "player/thruster/Thruster_00016.png",
        "player/thruster/Thruster_00017.png",
        "player/thruster/Thruster_00018.png",
        "player/thruster/Thruster_00019.png",
        "player/thruster/Thruster_00020.png")