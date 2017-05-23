package logic.env

import logic.agent.simulation_game_dependent.SimulationGameAgent
import logic.env.game2048.Simulation
import logic.sequence.ImmutableGame
import logic.sequence.Player

/**
 * - mutable
 *
 * @param
 * @return
 */
class EnvironmenSimu<in Map, Move>
(
        private val agent: SimulationGameAgent<Map, Move>,
        private val simulation: Simulation<Map, Move>
) : Player<ImmutableGame<Map, Move>, Move> {

    override fun move(immutableGame: ImmutableGame<Map, Move>) =
        agent.move(simulation.apply { map = immutableGame.map })
}