package base.mill.stage

import base.mill.MoveArray
import base.mill.mill
import base.mill.move
import base.mill.player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovingTest {
    @Test
    fun move() {
        assertEquals(
                mill(
                        player(0, Moving, 0, 0b000_000_010_100_001_100_000_100),
                        player(1, Moving, 0, 0b000_000_000_010_110_000_000_010)
                ),
                Moving.moved(
                        mill(
                                player(1, Moving, 0, 0b000_000_000_010_110_000_000_001),
                                player(0, Moving, 0, 0b000_000_010_100_001_100_000_100)
                        ),
                        move(0, 1)
                )
        )
    }

    @Test
    fun toRemoving() {
        assertEquals(
                mill(
                        player(0, Removing, 0, 0b000_000_000_010_110_010_010_010),
                        player(1, Moving, 0, 0b000_000_010_100_001_100_000_100)
                ),
                Moving.moved(
                        mill(
                                player(0, Moving, 0, 0b000_000_000_010_110_010_010_001),
                                player(1, Moving, 0, 0b000_000_010_100_001_100_000_100)
                        ),
                        move(0, 1)
                )
        )
    }

    @Test
    fun otherKeepsMoving() {
        assertEquals(
                mill(
                        player(0, Moving, 0, 0b000_000_010_100_001_100_000_100),
                        player(1, Moving, 0, 0b000_000_000_010_110_000_000_010)
                ),
                Moving.moved(
                        mill(
                                player(1, Moving, 0, 0b000_000_000_010_110_000_000_001),
                                player(0, Moving, 0, 0b000_000_010_100_001_100_000_100)
                        ),
                        move(0, 1)
                )
        )
    }

    @Test
    fun otherKeepsJumping() {
        assertEquals(
                mill(
                        player(0, Jumping, 0, 0b000_000_010_100_001_000_000_000),
                        player(1, Moving, 0, 0b000_000_000_010_110_000_000_010)
                ),
                Moving.moved(
                        mill(
                                player(1, Moving, 0, 0b000_000_000_010_110_000_000_001),
                                player(0, Jumping, 0, 0b000_000_010_100_001_000_000_000)
                        ),
                        move(0, 1)
                )
        )
    }

    @Test
    fun otherKeepsSetting() {
        assertEquals(
                mill(
                        player(0, Setting, 1, 0b010_110_010_100_001_101_000_000),
                        player(1, Moving, 0, 0b000_000_000_010_110_000_000_010)
                ),
                Moving.moved(
                        mill(
                                player(1, Moving, 0, 0b000_000_000_010_110_000_000_001),
                                player(0, Setting, 1, 0b010_110_010_100_001_101_000_000)
                        ),
                        move(0, 1)
                )
        )
    }

    @Test
    fun moves() {
        assertEquals(
                MoveArray(
                        intArrayOf(
                                move(9, 0), move(9, 10), move(9, 21),
                                move(15, 11),
                                move(19, 22)
                        )
                ),
                Moving.moves(
                        mill(
                                player(0, Moving, 0,  0b000_110_001_000_001_000_000_000),
                                player(1, Setting, 1, 0b100_001_010_011_000_000_000_000)
                        ),
                        MoveArray()
                )
        )
    }
}