package logic.information.game.mill

import base.mill.*
import logic.information.Event
import logic.information.Information
import logic.information.Informed
import logic.sequence.mill.MillWrapper

/**
 * - mutable
 *
 * @param
 * @return
 */
class InformedMill(private val game: MillWrapper) : MillWrapper by game, Informed<MillWrapper> {
    private val info: MutableList<Information<MillWrapper>> = ArrayList()

    override fun moved(move: Move) {
        info.forEach { it.update(Event.PRE_MOVE, this) }
        game.moved(move)
        info.forEach { it.update(Event.POST_MOVE, this) }
    }

    override fun reset() {
        info.forEach { it.update(Event.POST_GAME, this) }
        game.reset()
    }

    override fun addAll(vararg information: Information<MillWrapper>) {
        info.addAll(information)
    }

    override fun remove(index: Int) {
        assert(index in info.indices)
        info.removeAt(index)
    }

    override fun toString() = "$game\nGameInfo$info"
}