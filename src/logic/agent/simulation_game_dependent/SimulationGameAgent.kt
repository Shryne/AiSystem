package logic.agent.simulation_game_dependent

import logic.env.game2048.Simulation

/**
 * - mutable
 *
 * @param
 * @return
 */
interface SimulationGameAgent<Map, Move> {
    fun move(simulation: Simulation<Map, Move>): Move
}