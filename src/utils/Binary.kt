package utils

/**
 * Returns the right most bit of the Int or zero if there isn't one.
 * source: Hacker's delight
 */
val Int.rightestBit get() = this and (-this)