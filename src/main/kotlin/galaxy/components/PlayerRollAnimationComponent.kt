package galaxy.components

import com.almasb.fxgl.dsl.image
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.texture.Texture
import galaxy.entities.PLAYER
import galaxy.playerTurnLeftAnim
import galaxy.playerTurnRightAnim
import javafx.scene.image.Image
import kotlin.math.sign


class PlayerRollAnimationComponent : Component() {

    private lateinit var movementComponent: PlayerMovementComponent
    private lateinit var texture: Texture
    private val textures = mutableListOf<Image>()
    private var rollAngle = 0

    override fun onAdded() {
        movementComponent = entity.getComponent(PlayerMovementComponent::class.java)
        loadTextures()
        entity.transformComponent.scaleX = PLAYER.size / texture.height
        entity.transformComponent.scaleY = PLAYER.size / texture.width
        entity.viewComponent.addChild(texture)
    }

    private fun loadTextures() {
        textures.addAll(playerTurnLeftAnim.toList().reversed().map { (_, value) -> image(value) })
        textures.addAll(playerTurnRightAnim.toList().drop(1).map { (_, value) -> image(value) })

        texture = Texture(textures[textures.size / 2])
    }

    override fun onUpdate(tpf: Double) {

        getRollAngle()

        texture.image = textures[rollAngle + textures.size / 2]
    }

    private fun getRollAngle() {
        rollAngle = if (movementComponent.currentDirection.x == 0.0)
            if (rollAngle == 0)
                0
            else
                (sign(rollAngle.toDouble()) * (kotlin.math.abs(rollAngle) - 1)).toInt()
        else
            rollAngle + movementComponent.currentDirection.x.toInt()
        if (rollAngle > textures.size / 2) rollAngle = textures.size / 2
        if (rollAngle < -textures.size / 2) rollAngle = -textures.size / 2
    }

    override fun isComponentInjectionRequired() = false
}