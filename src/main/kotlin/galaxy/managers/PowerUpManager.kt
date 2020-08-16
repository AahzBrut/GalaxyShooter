package galaxy.managers

import com.almasb.fxgl.dsl.getAppWidth
import com.almasb.fxgl.dsl.geti
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.SpawnData
import galaxy.GalaxyEntityType
import galaxy.PowerUpType
import galaxy.entity_data.ENEMY
import galaxy.entity_data.POWER_UP

class PowerUpManager {

    private var start = 0

    fun spawnPowerUp(tpf: Double) {

        val spawnRange = IntRange(0, (getAppWidth() - ENEMY.size).toInt())
        val powerUpType = PowerUpType.values()[(0..2).random()]

        if ((geti("score") - start) / POWER_UP.scoreToSpawn >= 1) {
            spawn(
                GalaxyEntityType.POWER_UP.typeName(),
                SpawnData(spawnRange.random().toDouble(), 0.0).put("type", powerUpType)
            )
            start = (geti("score") / POWER_UP.scoreToSpawn) * POWER_UP.scoreToSpawn
        }
    }
}