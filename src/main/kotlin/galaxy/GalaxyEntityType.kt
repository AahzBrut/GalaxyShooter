package galaxy

enum class GalaxyEntityType {
    BACKGROUND,
    PLAYER,
    LASER_BOLT,
    ENEMY,
    EXPLOSION,
    POWER_UP;

    fun typeName(): String {
        var wordStart = true
        return this.toString().fold(StringBuilder(name.length)) { acc, c ->
            when {
                c == '_' -> {
                    wordStart = true
                    acc
                }
                wordStart -> {
                    wordStart = false
                    acc.append(c.toUpperCase())
                }
                else -> acc.append(c.toLowerCase())
            }
        }.toString()
    }
}