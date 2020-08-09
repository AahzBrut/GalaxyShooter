package galaxy.controllers

import com.almasb.fxgl.dsl.newLocalTimer
import com.almasb.fxgl.time.LocalTimer
import galaxy.GalaxyEntityType
import galaxy.entities.ENEMY_SPAWN
import galaxy.spawnEntityType
import javafx.util.Duration

class EnemyController(private val spawnTimer: LocalTimer) {

    @Suppress("UNUSED_PARAMETER")
    fun spawnEnemies(tpf: Double) {
        if (!spawnTimer.elapsed(Duration.seconds(ENEMY_SPAWN.spawnDelay))) return

        spawnEntityType(GalaxyEntityType.ENEMY)
    }
}