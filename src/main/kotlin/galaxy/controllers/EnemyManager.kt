package galaxy.controllers

import com.almasb.fxgl.dsl.getAppWidth
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.time.LocalTimer
import galaxy.GalaxyEntityType
import galaxy.entity_data.ENEMY
import galaxy.entity_data.ENEMY_SPAWN
import javafx.util.Duration

class EnemyManager(private val spawnTimer: LocalTimer) {

    @Suppress("UNUSED_PARAMETER")
    fun spawnEnemies(tpf: Double) {
        if (!spawnTimer.elapsed(Duration.seconds(ENEMY_SPAWN.spawnDelay))) return
        spawnTimer.capture()

        val spawnRange = IntRange(0, (getAppWidth() - ENEMY.size).toInt())
        spawn(GalaxyEntityType.ENEMY.typeName, spawnRange.random().toDouble(), -ENEMY.size)
    }
}