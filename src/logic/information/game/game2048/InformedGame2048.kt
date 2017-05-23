package logic.information.game.game2048

import base._2048.Game2048
import base._2048.Move2048
import logic.information.Event
import logic.information.Information
import logic.information.Informed

/**
 * - mutable
 *
 * @param
 * @return
 */
class InformedGame2048<out M>(val game: Game2048<M>) : Game2048<M> by game, Informed<Game2048<M>> {
    private val info: MutableList<Information<Game2048<M>>> = ArrayList()

    override fun progress(move: Move2048) {
        info.forEach { it.update(Event.PRE_MOVE, this) }
        game.progress(move)
        info.forEach { it.update(Event.POST_MOVE, this) }
    }

    override fun restart() {
        info.forEach { it.update(Event.POST_GAME, this) }
        game.restart()
    }

    override fun addAll(vararg information: Information<Game2048<M>>) {
        info.addAll(information)
    }

    override fun remove(index: Int) {
        assert(index in info.indices)
        info.removeAt(index)
    }

    override fun toString() = "$game\nGameInfo$info"
}