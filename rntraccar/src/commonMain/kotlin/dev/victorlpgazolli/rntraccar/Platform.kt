package dev.victorlpgazolli.rntraccar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform