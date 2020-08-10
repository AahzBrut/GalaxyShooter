package galaxy.components

import com.almasb.fxgl.entity.component.Component

class HealthComponent(maxHealthPoints: Int) : Component() {

    private var health = maxHealthPoints

    fun takeDamage(damage: Int){
        health -= damage

        if (health <= 0) {
            killEntity()
        } else {
            injureEntity(health)
        }
    }

    private fun injureEntity(health: Int) {
        TODO("Not yet implemented")
    }

    private fun killEntity() {
        entity.removeFromWorld()
    }

    override fun isComponentInjectionRequired() = false
}