package base

/**
 *
 * @param
 * @return
 */
interface BoardView {
    val length: Int
    operator fun get(index: Int): Int
}