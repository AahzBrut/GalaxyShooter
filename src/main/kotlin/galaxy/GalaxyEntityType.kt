package galaxy

import java.util.concurrent.atomic.AtomicBoolean

enum class GalaxyEntityType {
    BACKGROUND,
    PLAYER,
    LASER_BOLT,
    ENEMY,
    EXPLOSION;

    fun typeName(): String {
        val wordStart = AtomicBoolean(true)
        return this.toString().fold(StringBuilder(name.length)) { acc, c ->
            when {
                c == '_' -> {
                    wordStart.set(true)
                    acc
                }
                wordStart.getAndSet(false) -> acc.append(c.toUpperCase())
                else -> acc.append(c.toLowerCase())
            }
        }.toString()
    }
}