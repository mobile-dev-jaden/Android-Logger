package dev.util.logger

sealed class LoggerLevel {
    abstract val value: Int
    object Verbose: LoggerLevel() {
        override val value: Int get() = 1
    }

    object Debug: LoggerLevel() {
        override val value: Int get() = 2
    }

    object Information: LoggerLevel() {
        override val value: Int get() = 3
    }

    object Warning: LoggerLevel() {
        override val value: Int get() = 4
    }

    object Error: LoggerLevel() {
        override val value: Int get() = 5
    }
}
