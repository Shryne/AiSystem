package base._2048

/**
 * - mutable because of progress and restart
 *
 * This interface represents the 2048 game. It is supposed to be mutable,
 * to give some slight performance improvement, as it is necessary for the
 * model based agents.
 */
interface Game2048<out M> {
    val highestTile: Int

    /**
     * This operation won't return a new list, but keep the old one and update it in case of any changes.
     */
    val map: M

    /**
     * @return The current score of the game. It is calculated by adding the result
     * of all merged fields to the score.
     * @sample score: 100, 32 x 32 -> 64 (merge) => 100 + 64 = 164
     */
    val score: Int

    /**
     * This operation won't return a new list, but keep the old one and update it in case of any changes.
     *
     * @return all moves that would change the map if executed by progress(move).
     * result.isEmpty() == true means the game ist over.
     */
    val possibleMoves: ImmutablePossibleMovesArray<Move2048>

    /**
     * Convenience method. Same as possibleMoves.isEmpty()
     */
    val isOver: Boolean

    /**
     * To play the game, one has to use this method. It executes the given move
     * and spawns a new field (2 - 90%, 4 - 10%) on a randomly chosen with 0 filled.
     * If there isn't any possible move left, isRunning turns into false and the
     * game is over. It's still possible to call this method, but it's state won't be
     * changed.
     *
     * @param move The move to be executed.
     * @throws IllegalArgumentException if the given move didn't change the game.
     */
    fun progress(move: Move2048)

    /**
     * Starts a new game. (score = 0 and an empty map except two randomly spawned fields)
     */
    fun restart()
}