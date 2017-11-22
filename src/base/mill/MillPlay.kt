package base.mill

/**
 * - mutable, because the [progress] method changes the state of the object
 *
 * This interface defines the playing part of the mill game. To create a full name, one would need this interface and
 * the [MillView]. This separation was made to shrink the interface and give the user the opportunity to pass the
 * immutable part around if necessary without worrying that it could be altered by the accepting method/class.
 */
interface MillPlay : MillView<Int> {
    /**
     * - mutable, because the result of this method will be saved on the implementing object
     *
     * Used to play one move on mill.
     * @param move: The move to be played.
     * @throws AssertionError If the given move wasn't possible ([MillView] -> moves).
     */
    fun progress(moveBoard: Int)

    /**
     * - mutable, because the result of this method will be saved on the implementing object
     *
     * Resets the game.
     */
    fun reset()
}