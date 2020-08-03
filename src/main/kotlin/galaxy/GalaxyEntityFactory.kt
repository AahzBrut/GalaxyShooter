package galaxy

import com.almasb.fxgl.dsl.entityBuilder
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.EntityFactory
import com.almasb.fxgl.entity.SpawnData

import com.almasb.fxgl.entity.Spawns




class GalaxyEntityFactory : EntityFactory {

    @Spawns("Background")
    fun newBackground(data: SpawnData): Entity {
        return entityBuilder()
            .at(-10.0, -10.0) // bigger than game size to account for camera shake
            .view(texture("SpaceBG_Overlay.png", 820.0, 620.0))
            .zIndex(-500)
            .build()
    }

}