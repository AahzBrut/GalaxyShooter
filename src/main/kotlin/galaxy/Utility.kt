package galaxy

import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.SpawnData
import javafx.geometry.Point2D


fun spawnEntityType(entityType: GalaxyEntityType) = spawn(entityType.typeName, SpawnData(entityType.spawnPosition).put("baseTexture", entityType.baseTexture))


val LEFT = Point2D(-1.0, 0.0)
val RIGHT = Point2D(1.0, 0.0)
val UP = Point2D(0.0, -1.0)
val DOWN = Point2D(0.0, 1.0)
const val K: Long = 1_000

const val enemyTexture = "enemy/enemy.png"

val playerRollAnim = listOf(
        "player/turn-left/Player Turn Left0008.png",
        "player/turn-left/Player Turn Left0007.png",
        "player/turn-left/Player Turn Left0006.png",
        "player/turn-left/Player Turn Left0005.png",
        "player/turn-left/Player Turn Left0004.png",
        "player/turn-left/Player Turn Left0003.png",
        "player/turn-left/Player Turn Left0002.png",
        "player/turn-left/Player Turn Left0001.png",
        "player/turn-left/Player Turn Left0000.png",
        "player/turn-right/Player Turn Right0001.png",
        "player/turn-right/Player Turn Right0002.png",
        "player/turn-right/Player Turn Right0003.png",
        "player/turn-right/Player Turn Right0004.png",
        "player/turn-right/Player Turn Right0005.png",
        "player/turn-right/Player Turn Right0006.png",
        "player/turn-right/Player Turn Right0007.png",
        "player/turn-right/Player Turn Right0008.png")

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

val enemyExplosion = listOf(
        "enemy/explode/EnemyTurnLeft0000_00000.png",
        "enemy/explode/EnemyTurnLeft0000_00001.png",
        "enemy/explode/EnemyTurnLeft0000_00002.png",
        "enemy/explode/EnemyTurnLeft0000_00003.png",
        "enemy/explode/EnemyTurnLeft0000_00004.png",
        "enemy/explode/EnemyTurnLeft0000_00005.png",
        "enemy/explode/EnemyTurnLeft0000_00006.png",
        "enemy/explode/EnemyTurnLeft0000_00007.png",
        "enemy/explode/EnemyTurnLeft0000_00008.png",
        "enemy/explode/EnemyTurnLeft0000_00009.png",
        "enemy/explode/EnemyTurnLeft0000_00010.png",
        "enemy/explode/EnemyTurnLeft0000_00011.png",
        "enemy/explode/EnemyTurnLeft0000_00012.png",
        "enemy/explode/EnemyTurnLeft0000_00013.png",
        "enemy/explode/EnemyTurnLeft0000_00014.png",
        "enemy/explode/EnemyTurnLeft0000_00015.png",
        "enemy/explode/EnemyTurnLeft0000_00016.png",
        "enemy/explode/EnemyTurnLeft0000_00017.png",
        "enemy/explode/EnemyTurnLeft0000_00018.png",
        "enemy/explode/EnemyTurnLeft0000_00019.png",
        "enemy/explode/EnemyTurnLeft0000_00020.png",
        "enemy/explode/EnemyTurnLeft0000_00021.png",
        "enemy/explode/EnemyTurnLeft0000_00022.png",
        "enemy/explode/EnemyTurnLeft0000_00023.png",
        "enemy/explode/EnemyTurnLeft0000_00024.png",
        "enemy/explode/EnemyTurnLeft0000_00025.png",
        "enemy/explode/EnemyTurnLeft0000_00026.png",
        "enemy/explode/EnemyTurnLeft0000_00027.png",
        "enemy/explode/EnemyTurnLeft0000_00028.png",
        "enemy/explode/EnemyTurnLeft0000_00029.png",
        "enemy/explode/EnemyTurnLeft0000_00030.png",
        "enemy/explode/EnemyTurnLeft0000_00031.png",
        "enemy/explode/EnemyTurnLeft0000_00032.png",
        "enemy/explode/EnemyTurnLeft0000_00033.png",
        "enemy/explode/EnemyTurnLeft0000_00034.png",
        "enemy/explode/EnemyTurnLeft0000_00035.png",
        "enemy/explode/EnemyTurnLeft0000_00036.png",
        "enemy/explode/EnemyTurnLeft0000_00037.png",
        "enemy/explode/EnemyTurnLeft0000_00038.png",
        "enemy/explode/EnemyTurnLeft0000_00039.png",
        "enemy/explode/EnemyTurnLeft0000_00040.png",
        "enemy/explode/EnemyTurnLeft0000_00041.png",
        "enemy/explode/EnemyTurnLeft0000_00042.png",
        "enemy/explode/EnemyTurnLeft0000_00043.png",
        "enemy/explode/EnemyTurnLeft0000_00044.png",
        "enemy/explode/EnemyTurnLeft0000_00045.png",
        "enemy/explode/EnemyTurnLeft0000_00046.png",
        "enemy/explode/EnemyTurnLeft0000_00047.png",
        "enemy/explode/EnemyTurnLeft0000_00048.png",
        "enemy/explode/EnemyTurnLeft0000_00049.png",
        "enemy/explode/EnemyTurnLeft0000_00050.png",
        "enemy/explode/EnemyTurnLeft0000_00051.png",
        "enemy/explode/EnemyTurnLeft0000_00052.png",
        "enemy/explode/EnemyTurnLeft0000_00053.png",
        "enemy/explode/EnemyTurnLeft0000_00054.png",
        "enemy/explode/EnemyTurnLeft0000_00055.png",
        "enemy/explode/EnemyTurnLeft0000_00056.png",
        "enemy/explode/EnemyTurnLeft0000_00057.png",
        "enemy/explode/EnemyTurnLeft0000_00058.png",
        "enemy/explode/EnemyTurnLeft0000_00059.png",
        "enemy/explode/EnemyTurnLeft0000_00060.png",
        "enemy/explode/EnemyTurnLeft0000_00061.png",
        "enemy/explode/EnemyTurnLeft0000_00062.png",
        "enemy/explode/EnemyTurnLeft0000_00063.png",
        "enemy/explode/EnemyTurnLeft0000_00064.png",
        "enemy/explode/EnemyTurnLeft0000_00065.png",
        "enemy/explode/EnemyTurnLeft0000_00066.png",
        "enemy/explode/EnemyTurnLeft0000_00067.png",
        "enemy/explode/EnemyTurnLeft0000_00068.png",
        "enemy/explode/EnemyTurnLeft0000_00069.png",
        "enemy/explode/EnemyTurnLeft0000_00070.png",
        "enemy/explode/EnemyTurnLeft0000_00071.png",
        "enemy/explode/EnemyTurnLeft0000_00072.png",
        "enemy/explode/EnemyTurnLeft0000_00073.png",
        "enemy/explode/EnemyTurnLeft0000_00074.png",
        "enemy/explode/EnemyTurnLeft0000_00075.png",
        "enemy/explode/EnemyTurnLeft0000_00076.png",
        "enemy/explode/EnemyTurnLeft0000_00077.png",
        "enemy/explode/EnemyTurnLeft0000_00078.png",
        "enemy/explode/EnemyTurnLeft0000_00079.png",
        "enemy/explode/EnemyTurnLeft0000_00080.png",
        "enemy/explode/EnemyTurnLeft0000_00081.png",
        "enemy/explode/EnemyTurnLeft0000_00082.png",
        "enemy/explode/EnemyTurnLeft0000_00083.png",
        "enemy/explode/EnemyTurnLeft0000_00084.png",
        "enemy/explode/EnemyTurnLeft0000_00085.png",
        "enemy/explode/EnemyTurnLeft0000_00086.png",
        "enemy/explode/EnemyTurnLeft0000_00087.png",
        "enemy/explode/EnemyTurnLeft0000_00088.png",
        "enemy/explode/EnemyTurnLeft0000_00089.png",
        "enemy/explode/EnemyTurnLeft0000_00090.png",
        "enemy/explode/EnemyTurnLeft0000_00091.png",
        "enemy/explode/EnemyTurnLeft0000_00092.png",
        "enemy/explode/EnemyTurnLeft0000_00093.png",
        "enemy/explode/EnemyTurnLeft0000_00094.png",
        "enemy/explode/EnemyTurnLeft0000_00095.png",
        "enemy/explode/EnemyTurnLeft0000_00096.png",
        "enemy/explode/EnemyTurnLeft0000_00097.png",
        "enemy/explode/EnemyTurnLeft0000_00098.png",
        "enemy/explode/EnemyTurnLeft0000_00099.png",
        "enemy/explode/EnemyTurnLeft0000_00100.png",
        "enemy/explode/EnemyTurnLeft0000_00101.png",
        "enemy/explode/EnemyTurnLeft0000_00102.png",
        "enemy/explode/EnemyTurnLeft0000_00103.png",
        "enemy/explode/EnemyTurnLeft0000_00104.png",
        "enemy/explode/EnemyTurnLeft0000_00105.png",
        "enemy/explode/EnemyTurnLeft0000_00106.png",
        "enemy/explode/EnemyTurnLeft0000_00107.png",
        "enemy/explode/EnemyTurnLeft0000_00108.png",
        "enemy/explode/EnemyTurnLeft0000_00109.png",
        "enemy/explode/EnemyTurnLeft0000_00110.png",
        "enemy/explode/EnemyTurnLeft0000_00111.png",
        "enemy/explode/EnemyTurnLeft0000_00112.png",
        "enemy/explode/EnemyTurnLeft0000_00113.png",
        "enemy/explode/EnemyTurnLeft0000_00114.png",
        "enemy/explode/EnemyTurnLeft0000_00115.png",
        "enemy/explode/EnemyTurnLeft0000_00116.png",
        "enemy/explode/EnemyTurnLeft0000_00117.png",
        "enemy/explode/EnemyTurnLeft0000_00118.png",
        "enemy/explode/EnemyTurnLeft0000_00119.png",
        "enemy/explode/EnemyTurnLeft0000_00120.png",
        "enemy/explode/EnemyTurnLeft0000_00121.png",
        "enemy/explode/EnemyTurnLeft0000_00122.png",
        "enemy/explode/EnemyTurnLeft0000_00123.png",
        "enemy/explode/EnemyTurnLeft0000_00124.png",
        "enemy/explode/EnemyTurnLeft0000_00125.png",
        "enemy/explode/EnemyTurnLeft0000_00126.png",
        "enemy/explode/EnemyTurnLeft0000_00127.png",
        "enemy/explode/EnemyTurnLeft0000_00128.png",
        "enemy/explode/EnemyTurnLeft0000_00129.png",
        "enemy/explode/EnemyTurnLeft0000_00130.png",
        "enemy/explode/EnemyTurnLeft0000_00131.png",
        "enemy/explode/EnemyTurnLeft0000_00132.png",
        "enemy/explode/EnemyTurnLeft0000_00133.png",
        "enemy/explode/EnemyTurnLeft0000_00134.png",
        "enemy/explode/EnemyTurnLeft0000_00135.png",
        "enemy/explode/EnemyTurnLeft0000_00136.png",
        "enemy/explode/EnemyTurnLeft0000_00137.png",
        "enemy/explode/EnemyTurnLeft0000_00138.png",
        "enemy/explode/EnemyTurnLeft0000_00139.png",
        "enemy/explode/EnemyTurnLeft0000_00140.png",
        "enemy/explode/EnemyTurnLeft0000_00141.png",
        "enemy/explode/EnemyTurnLeft0000_00142.png",
        "enemy/explode/EnemyTurnLeft0000_00143.png",
        "enemy/explode/EnemyTurnLeft0000_00144.png",
        "enemy/explode/EnemyTurnLeft0000_00145.png",
        "enemy/explode/EnemyTurnLeft0000_00146.png",
        "enemy/explode/EnemyTurnLeft0000_00147.png",
        "enemy/explode/EnemyTurnLeft0000_00148.png",
        "enemy/explode/EnemyTurnLeft0000_00149.png",
        "enemy/explode/EnemyTurnLeft0000_00150.png",
        "enemy/explode/EnemyTurnLeft0000_00151.png",
        "enemy/explode/EnemyTurnLeft0000_00152.png",
        "enemy/explode/EnemyTurnLeft0000_00153.png",
        "enemy/explode/EnemyTurnLeft0000_00154.png",
        "enemy/explode/EnemyTurnLeft0000_00155.png",
        "enemy/explode/EnemyTurnLeft0000_00156.png",
        "enemy/explode/EnemyTurnLeft0000_00157.png")
