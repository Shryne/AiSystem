package logic.sequence

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Player {
    fun move(possibleMoves: ImmutablePossibleMovesArray): Int
    fun onGameOver()
}