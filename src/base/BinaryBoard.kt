package base

/**
 * - mutable
 *
 * @param
 * @return
 */
class BinaryBoard(var board: Long, override val length: Int, private val bitsPerTile: Int) : Board {
    override fun get(index: Int) =
            ((board shr bitsPerTile * ((length * length) - index - 1) shl java.lang.Long.SIZE - bitsPerTile)
                    .ushr(java.lang.Long.SIZE - bitsPerTile)).toInt()

    override fun set(index: Int, value: Int) {
        board = board xor (value.toLong() shl ((length * length) - index - 1) * bitsPerTile)
    }
}