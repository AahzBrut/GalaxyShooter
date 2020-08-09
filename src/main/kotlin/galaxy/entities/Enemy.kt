package galaxy.entities

data class Enemy(
        val size: Double = 64.0
)

val ENEMY = Enemy()

data class EnemySpawn(
        val spawnDelay: Double = 1.0
)

val ENEMY_SPAWN = EnemySpawn()