package base._2048

/**
 * - mutable
 *
 * @param
 * @return
 */
enum class Move2048 {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    companion object {
        const val AMOUNT = 4
    }
}