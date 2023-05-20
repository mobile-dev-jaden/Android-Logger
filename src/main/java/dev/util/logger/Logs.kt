package dev.util.logger

import android.util.Log
import dev.util.logger.util.DoNothing

/**
 * Singleton class
 */
class Logs private constructor() {
    companion object {
        private val instance: Logs = Logs()
        val configuration: Builder = Builder()
        private const val DOUBLE_DIVIDER =
            "────────────────────────────────────────────────────────"

        private const val LOG_PRETTY_COVER_UP = "┌$DOUBLE_DIVIDER"
        private const val HORIZONTAL_LINE = '│'
        private const val LOG_PRETTY_COVER_BOTTOM = "└$DOUBLE_DIVIDER"
        private const val NEW_LINE_SEPARATOR = "\n"

        @Synchronized
        fun get(): Logs = instance

        private const val DEFAULT_LOGGER_TAG = "Logging-Tag"

        // Verbose
        fun v(message: String) = get().v(message)

        // Debug
        fun d(message: String) = get().d(message)

        // Info
        fun i(message: String) = get().i(message)

        // Warning
        fun w(message: String) = get().w(message)

        // Error
        fun e(message: String) = get().e(message)

        fun print(message: String, loggerLevel: LoggerLevel = LoggerLevel.Debug) {
            get().run {
                when (loggerLevel) {
                    LoggerLevel.Verbose -> v(message)
                    LoggerLevel.Debug -> d(message)
                    LoggerLevel.Information -> i(message)
                    LoggerLevel.Warning -> w(message)
                    LoggerLevel.Error -> e(message)
                }
            }
        }
    }

    private var level: LoggerLevel = LoggerLevel.Verbose

    private var isDebug: Boolean = true

    private var tag: String? = null
    private val _tag: String
        get() = tag ?: DEFAULT_LOGGER_TAG

    private fun setLoggerLevel(level: LoggerLevel) {
        this.level = level
    }

    private fun setTag(tag: String) {
        this.tag = tag
    }

    private fun isDebugVersion(isDebug: Boolean) {
        this.isDebug = isDebug
    }

    class Builder {
        fun isDebugVersion(isDebug: Boolean): Builder {
            get().isDebugVersion(isDebug)
            return this
        }

        fun setLoggerLevel(loggerLevel: LoggerLevel): Builder {
            get().setLoggerLevel(loggerLevel)
            return this
        }

        fun setTag(tag: String): Builder {
            get().setTag(tag)
            return this
        }

        fun build(): Logs {
            return get()
        }
    }

    fun v(message: String) {
        if (isDebug) {
            if (level.value <= LoggerLevel.Verbose.value) {
                Log.v(_tag, LOG_PRETTY_COVER_UP)
                message
                    .split(NEW_LINE_SEPARATOR)
                    .forEach { Log.v(_tag, "$HORIZONTAL_LINE $it") }
                Log.v(_tag, LOG_PRETTY_COVER_BOTTOM)
            } else {
                DoNothing
            }
        } else {
            DoNothing
        }
    }

    fun i(message: String) {
        if (isDebug) {
            if (level.value <= LoggerLevel.Information.value) {
                Log.i(_tag, LOG_PRETTY_COVER_UP)
                message
                    .split(NEW_LINE_SEPARATOR)
                    .forEach { Log.i(_tag, "$HORIZONTAL_LINE $it") }
                Log.i(_tag, LOG_PRETTY_COVER_BOTTOM)
            } else {
                DoNothing
            }
        } else {
            DoNothing
        }
    }

    fun w(message: String) {
        if (isDebug) {
            if (level.value <= LoggerLevel.Warning.value) {
                Log.w(_tag, LOG_PRETTY_COVER_UP)
                message
                    .split(NEW_LINE_SEPARATOR)
                    .forEach { Log.w(_tag, "$HORIZONTAL_LINE $it") }
                Log.w(_tag, LOG_PRETTY_COVER_BOTTOM)
            } else {
                DoNothing
            }
        } else {
            DoNothing
        }
    }

    fun e(message: String) {
        if (isDebug) {
            if (level.value <= LoggerLevel.Error.value) {
                Log.e(_tag, LOG_PRETTY_COVER_UP)
                message
                    .split(NEW_LINE_SEPARATOR)
                    .forEach { Log.e(_tag, "$HORIZONTAL_LINE $it") }
                Log.e(_tag, LOG_PRETTY_COVER_BOTTOM)
            } else {
                DoNothing
            }
        } else {
            DoNothing
        }
    }

    fun d(message: String) {
        if (isDebug) {
            if (level.value <= LoggerLevel.Debug.value) {
                Log.d(_tag, LOG_PRETTY_COVER_UP)
                message
                    .split(NEW_LINE_SEPARATOR)
                    .forEach { Log.d(_tag, "$HORIZONTAL_LINE $it") }
                Log.d(_tag, LOG_PRETTY_COVER_BOTTOM)
            } else {
                DoNothing
            }
        } else {
            DoNothing
        }
    }
}
