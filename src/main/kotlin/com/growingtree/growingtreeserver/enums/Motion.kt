package com.growingtree.growingtreeserver.enums

enum class Motion {
    WALK,
    RUN,
    JUMP,
    CLAP,
    SIT,
    CROSS_ARMS,
    SHAKE_HANDS, ;

    companion object {
        fun fromString(motion: String): Motion =
            when (motion) {
                "walk" -> WALK
                "run" -> RUN
                "jump" -> JUMP
                "clap" -> CLAP
                "sit" -> SIT
                "cross" -> CROSS_ARMS
                else -> SHAKE_HANDS
            }
    }
}
