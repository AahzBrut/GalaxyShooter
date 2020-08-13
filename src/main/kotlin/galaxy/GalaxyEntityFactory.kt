package galaxy

import com.almasb.fxgl.dsl.entityBuilder
import com.almasb.fxgl.dsl.getGameScene
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityFactory
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.Spawns
import com.almasb.fxgl.entity.components.BoundingBoxComponent
import com.almasb.fxgl.entity.components.CollidableComponent
import com.almasb.fxgl.physics.BoundingShape
import com.almasb.fxgl.physics.HitBox
import galaxy.components.EnemyMovementComponent
import galaxy.components.EngineOnFireAnimationComponent
import galaxy.components.ExplosionAnimationComponent
import galaxy.components.HealthComponent
import galaxy.components.PlayerMovementComponent
import galaxy.components.PlayerRollAnimationComponent
import galaxy.components.PlayerThrusterAnimationComponent
import galaxy.components.PlayerWeaponComponent
import galaxy.components.ProjectileComponent
import galaxy.entity_data.ENEMY
import galaxy.entity_data.LASER_BOLT
import galaxy.entity_data.PLAYER
import javafx.geometry.Point2D
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

        val player = entityBuilder(SpawnData(GalaxyEntityType.PLAYER.spawnPosition))
                .type(GalaxyEntityType.PLAYER)
                .with(HealthComponent(PLAYER.maxHealth))
                .with(CollidableComponent(true))
                .with(PlayerMovementComponent())
                .with(PlayerRollAnimationComponent())
                .with(PlayerThrusterAnimationComponent())
                .with(PlayerWeaponComponent())
                .with(EngineOnFireAnimationComponent())
                .build()

        val boundingBox = player.getComponent(BoundingBoxComponent::class.java)
        val xSize = PLAYER.size * PLAYER.hitBoxScale * .65
        val ySize = PLAYER.size * PLAYER.hitBoxScale * .8
        boundingBox.addHitBox(HitBox(Point2D(xSize * .28, ySize * .1), BoundingShape.box(xSize, ySize)))

        return player
    }

    @Suppress("UNUSED")
    @Spawns("LaserBolt")
    fun newProjectile(data: SpawnData): Entity {

        val bolt = entityBuilder(data)
                .type(GalaxyEntityType.LASER_BOLT)
                .with(HealthComponent(LASER_BOLT.maxHealth))
                .with(CollidableComponent(true))
                .with(ProjectileComponent())
                .view(texture(GalaxyEntityType.LASER_BOLT.baseTexture, LASER_BOLT.size.x, LASER_BOLT.size.y))
                .build()

        val boundingBox = bolt.getComponent(BoundingBoxComponent::class.java)
        boundingBox.addHitBox(HitBox(Point2D(LASER_BOLT.size.x / 3, 0.0), BoundingShape.box(LASER_BOLT.size.x / 3, LASER_BOLT.size.y)))

        return bolt
    }

    @Suppress("UNUSED", "UNUSED_PARAMETER")
    @Spawns("Enemy")
    fun newEnemy(data: SpawnData): Entity {

        val enemyTexture = texture(GalaxyEntityType.ENEMY.baseTexture, ENEMY.size, ENEMY.size)

        val enemy = entityBuilder(data)
                .type(GalaxyEntityType.ENEMY)
                .with(HealthComponent(ENEMY.maxHealth))
                .with(CollidableComponent(true))
                .with(EnemyMovementComponent())
                .view(enemyTexture)
                .build()

        val boundingBox = enemy.getComponent(BoundingBoxComponent::class.java)
        boundingBox.addHitBox(HitBox(Point2D(ENEMY.size / 4, ENEMY.size / 4), BoundingShape.box(ENEMY.size / 2, ENEMY.size / 2)))

        return enemy
    }

    @Suppress("UNUSED")
    @Spawns("Explosion")
    fun newExplosion(data: SpawnData): Entity {

        return entityBuilder(data)
                .type(GalaxyEntityType.EXPLOSION)
                .with(ExplosionAnimationComponent(data.get("animation")))
                .build()
    }
}