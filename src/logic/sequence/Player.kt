package logic.sequence

/**
 * - mutable
 *
 * @param
 * @return
 */
interface Player<in G : ImmutableGame<*, M>, M> {
    fun move(immutableGame: G): M
}