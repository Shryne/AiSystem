package base.mill.stage

import base.EventualMemoryArray
import base.mill.Game
import base.mill.Mill
import base.mill.Move
import base.mill.Player

/**
 * A player of the mill game can be in different stages and his move possibilities are based on his current stage.
 * For each of this stages there is an immutable object which contains their own rules.
 *
 * The stages are all immutable singletons and i would even make functions of them, but I need this interface to
 * talk uniformly to them.
 *
 * This interface and the implementing classes conform to the strategy pattern.
 */
interface Stage {
    /**
     * The order of the stages. It's unique and consecutive without gaps. This number is needed to map the stages on a
     * number. It is used in the [Player] file to convert from and to a Stage based on this order.
     */
    val order: Int

    /**
     * Returns the moved version of the given mill game. The move is performed based on the actual stage.
     *
     * @param mill The game coded as described in [Mill]. Use the given methods of this file.
     * @param move The move of the game as in [Move]. Use the given methods of this file.
     * @return A new mill game with the executed move.
     */
    fun moved(mill: Mill, move: Move): Mill

    fun moves(mill:Mill, to: EventualMemoryArray<Move>): EventualMemoryArray<Move>

    /**
     * This method is needed, because some stages don't switch the players (= the same player can do multiple turns in
     * a row -> removing1/2). This method will be called by the stages to decide which player will do the next turn.
     * In case of Removing and 2 it will return the players in the same order (game(player, otherPlayer) and the other
     * stages will return the players in reversed order (game(otherPlayer, player)).
     * @param player The current player
     * @param otherPlayer
     * @return The game with the ordered players.
     */
    fun orderedPlayersGame(player: Player, otherPlayer: Player): Game
}