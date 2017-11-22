package base.mill

import base.BoardView
import base._2048.ImmutablePossibleMovesArray

/**
 * @param
 * @return
 */
interface MillView<out M> {
    val map: IntArray
    val currentPlayer: Player
    val possibleMoves: ImmutablePossibleMovesArray<M>
}