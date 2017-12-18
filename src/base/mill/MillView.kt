package base.mill

import base._2048.ImmutablePossibleMovesArray

/**
 * @param
 * @return
 */
interface MillView<out M> {
    val map: IntArray
    val player: Player
    val possibleMoves: ImmutablePossibleMovesArray<M>
}