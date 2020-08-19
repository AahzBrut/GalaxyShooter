package galaxy.managers

import com.almasb.fxgl.dsl.getAppWidth
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.time.LocalTimer
import galaxy.GalaxyEntityType
import galaxy.entity_data.ENEMY
import galaxy.entity_data.ENEMY_SPAWN
import javafx.util.Duration

class EnemyManager() {

    fun spawnEnemies() {

        val spawnRange = IntRange(0, (getAppWidth() - ENEMY.size).toInt())
        spawn(GalaxyEntityType.ENEMY.typeName(), spawnRange.random().toDouble(), -ENEMY.size)
    }
}