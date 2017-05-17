package logic.sequence

/**
 * - mutable
 *
 * @param
 * @return
 */
interface GameSequence : Sequence {
    override fun step(amount: Int)
}