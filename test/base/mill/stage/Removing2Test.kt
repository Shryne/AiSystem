package base.mill.stage

import base.mill.MoveArray
import base.mill.mill
import base.mill.move
import base.mill.player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Removing2Test {
    @Test
    fun removing2() {
        assertEquals(
                mill(
                        player(1, Removing1, 1, 0b000_000_000_010_100_010_010_111),
                        player(0, Moving, 0, 0b000_100_010_100_001_100_000_000)
                ),
                Removing2.moved(
                        mill(
                                player(1, Removing2, 1, 0b000_000_000_010_100_010_010_111),
                                player(0, Moving, 0, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun otherKeepsMoving() {
        assertEquals(
                mill(
                        player(1, Removing1, 1, 0b000_000_000_010_100_010_010_111),
                        player(0, Moving, 0, 0b000_100_010_100_001_100_000_000)
                ),
                Removing2.moved(
                        mill(
                                player(1, Removing2, 1, 0b000_000_000_010_100_010_010_111),
                                player(0, Moving, 0, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun otherKeepsSetting() {
        assertEquals(
                mill(
                        player(1, Removing1, 1, 0b000_000_000_010_100_010_010_111),
                        player(0, Setting, 1, 0b000_100_010_100_001_100_000_000)
                ),
                Removing2.moved(
                        mill(
                                player(1, Removing2, 1, 0b000_000_000_010_100_010_010_111),
                                player(0, Setting, 1, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun moves() {
        assertEquals(
                MoveArray(
                        intArrayOf(
                                0b000_000_000_001_000_000_000_000,
                                0b000_000_000_010_000_000_000_000,
                                0b000_000_010_000_000_000_000_000,
                                0b000_001_000_000_000_000_000_000,
                                0b100_000_000_000_000_000_000_000
                        )
                ),
                Removing2.moves(
                        mill(
                                player(0, Removing2, 5, 0b000_110_001_000_001_000_000_000),
                                player(1, Setting, 4, 0b100_001_010_011_000_000_000_000)
                        ),
                        MoveArray()
                )
        )
    }
}