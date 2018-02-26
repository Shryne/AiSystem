package base.mill.stage

import base.mill.MoveArray
import base.mill.mill
import base.mill.move
import base.mill.player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RemovingTest {
    @Test
    fun removing1() {
        assertEquals(
                mill(
                        player(0, Moving, 0, 0b000_100_010_100_001_100_000_000),
                        player(1, Setting, 1, 0b000_000_000_010_100_000_010_111)
                ),
                Removing.moved(
                        mill(
                                player(1, Removing, 1, 0b000_000_000_010_100_000_010_111),
                                player(0, Moving, 0, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun keepsSetting() {
        assertEquals(
                mill(
                        player(0, Moving, 0, 0b000_100_010_100_001_100_000_000),
                        player(1, Setting, 1, 0b000_000_000_010_100_000_010_111)
                ),
                Removing.moved(
                        mill(
                                player(1, Removing, 1, 0b000_000_000_010_100_000_010_111),
                                player(0, Moving, 0, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun keepsMoving() {
        assertEquals(
                mill(
                        player(0, Moving, 0, 0b000_100_010_100_001_100_000_000),
                        player(1, Moving, 0, 0b000_000_000_010_100_000_010_111)
                ),
                Removing.moved(
                        mill(
                                player(1, Removing, 0, 0b000_000_000_010_100_000_010_111),
                                player(0, Moving, 0, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun keepsJumping() {
        assertEquals(
                mill(
                        player(0, Moving, 0, 0b000_100_010_100_001_100_000_000),
                        player(1, Jumping, 0, 0b000_000_000_000_000_000_000_111)
                ),
                Removing.moved(
                        mill(
                                player(1, Removing, 0, 0b000_000_000_000_000_000_000_111),
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
                        player(0, Setting, 1, 0b000_100_010_100_001_100_000_000),
                        player(1, Setting, 1, 0b000_000_000_010_100_010_010_111)
                ),
                Removing.moved(
                        mill(
                                player(1, Removing, 1, 0b000_000_000_010_100_010_010_111),
                                player(0, Setting, 1, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun otherKeepsMoving() {
        assertEquals(
                mill(
                        player(0, Moving, 0, 0b000_100_010_100_001_100_000_000),
                        player(1, Setting, 1, 0b000_000_000_010_100_010_010_111)
                ),
                Removing.moved(
                        mill(
                                player(1, Removing, 1, 0b000_000_000_010_100_010_010_111),
                                player(0, Moving, 0, 0b000_100_010_100_001_100_001_000)
                        ),
                        move(3)
                )
        )
    }

    @Test
    fun otherKeepsJumping() {
        assertEquals(
                mill(
                        player(0, Jumping, 0, 0b000_000_000_000_001_100_000_000),
                        player(1, Setting, 1, 0b000_000_000_010_100_010_010_111)
                ),
                Removing.moved(
                        mill(
                                player(1, Removing, 1, 0b000_000_000_010_100_010_010_111),
                                player(0, Jumping, 0, 0b000_000_000_000_001_100_001_000)
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
                Removing.moves(
                        mill(
                                player(0, Removing, 5, 0b000_110_001_000_001_000_000_000),
                                player(1, Setting, 4, 0b100_001_010_011_000_000_000_000)
                        ),
                        MoveArray()
                )
        )
    }
}