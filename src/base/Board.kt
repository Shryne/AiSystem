package base

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Board : BoardView {
    operator fun set(index: Int, value: Int)
}