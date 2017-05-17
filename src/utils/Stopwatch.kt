package utils

/**
 * Represents a Stopwatch. Use start(...) before using the other methods and
 * use it to reset the time.
 *
 * One can avoid the typical
 * "startTime = System.nanoTime() -> System.nanoTime() - startTime" by using this
 * class.
 */
class Stopwatch {
    private var startTime: Long = 0L

    /**
     * Call this method to reset the timer. It's possible and intended to
     * use this method multiple times, especially if isTimeOver(...) is already
     * true to reset the timer without creating a new instance of this class.
     */
    fun start() {
        startTime = System.currentTimeMillis()
    }

    /**
     * Note that this method won't work as intended if start() hasn't been
     * called before.
     * @return true if the given time has passed since the start method
     * has been called.
     */
    fun isTimeOver(milliSeconds: Long) = (milliSeconds <= System.currentTimeMillis() - startTime)
}