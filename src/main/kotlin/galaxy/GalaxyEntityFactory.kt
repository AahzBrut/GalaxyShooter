package galaxy

import com.almasb.fxgl.dsl.entityBuilder
import com.almasb.fxgl.dsl.getGameScene
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityFactory
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.Spawns
import com.almasb.fxgl.entity.components.CollidableComponent
import galaxy.components.*
import galaxy.entity_data.ENEMY
import galaxy.entity_data.LASER_BOLT
import galaxy.entity_data.PLAYER
import javafx.scene.paint.Color


class GalaxyEntityFactory : EntityFactory {

    @Suppress("UNUSED", "UNUSED_PARAMETER")
    @Spawns("Background")
    fun newBackground(data: SpawnData): Entity {

        getGameScene().setBackgroundColor(Color.BLACK)

        return entityBuilder()
            .at(-10.0, -10.0)
            .view(texture(data.get("baseTexture"), 820.0, 620.0))
            .zIndex(-500)
            .build()
    }

    @Suppress("UNUSED", "UNUSED_PARAMETER")
    @Spawns("Player")
    fun newPlayer(data: SpawnData): Entity {

        return entityBuilder(SpawnData(GalaxyEntityType.PLAYER.spawnPosition))
                .type(GalaxyEntityType.PLAYER)
                .with(HealthComponent(PLAYER.maxHealth))
                .with(CollidableComponent(true))
                .with(PlayerMovementComponent())
                .with(PlayerRollAnimationComponent())
                .with(PlayerThrusterAnimationComponent())
                .with(PlayerWeaponComponent())
                .build()
    }

    @Suppress("UNUSED")
    @Spawns("LaserBolt")
    fun newProjectile(data: SpawnData): Entity {

        return entityBuilder(data)
                .type(GalaxyEntityType.LASER_BOLT)
                .with(HealthComponent(LASER_BOLT.maxHealth))
                .with(CollidableComponent(true))
                .with(ProjectileComponent())
                .view(texture(GalaxyEntityType.LASER_BOLT.baseTexture, LASER_BOLT.size.x, LASER_BOLT.size.y))
                .build()
    }

    @Suppress("UNUSED", "UNUSED_PARAMETER")
    @Spawns("Enemy")
    fun newEnemy(data: SpawnData): Entity {

        val enemyTexture = texture(GalaxyEntityType.ENEMY.baseTexture, ENEMY.size, ENEMY.size)
        enemyTexture.rotate = 180.0
        return entityBuilder(data)
                .type(GalaxyEntityType.ENEMY)
                .with(HealthComponent(ENEMY.maxHealth))
                .with(CollidableComponent(true))
                .with(EnemyMovementComponent())
                .view(enemyTexture)
                .build()
    }
}