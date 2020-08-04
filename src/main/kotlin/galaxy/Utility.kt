package galaxy

import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.SpawnData
import javafx.geometry.Point2D


fun spawnEntityType(entityType: GalaxyEntityType)  = spawn(entityType.typeName, SpawnData(entityType.spawnPosition).put("baseTexture", entityType.baseTexture))


val LEFT = Point2D(-1.0, 0.0)
val RIGHT = Point2D(1.0, 0.0)
val UP = Point2D(0.0, -1.0)
val DOWN = Point2D(0.0, 1.0)
