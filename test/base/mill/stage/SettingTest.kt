package base.mill.stage

import base.mill.MoveArray
import base.mill.mill
import base.mill.move
import base.mill.player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SettingTest {
    @Test
    fun firstOnEmpty() {
        assertEquals(
                mill(
                        player(1, Setting, 9, 0b000_000_000_000_000_000_000_000),
                        player(0, Setting, 8, 0b000_000_000_000_000_000_000_001)
                ),
                Setting.moved(
                        mill(
                                player(0, Setting, 9, 0b000_000_000_000_000_000_000_000),
                                player(1, Setting, 9, 0b000_000_000_000_000_000_000_000)
                        ),
                        move(0)
                )
        )
    }

    @Test
    fun lastOnEmpty() {
        assertEquals(
                mill(
                        player(1, Setting, 9, 0b000_000_000_000_000_000_000_000),
                        player(0, Setting, 8, 0b100_000_000_000_000_000_000_000)
                ),
                Setting.moved(
                        mill(
                                player(0, Setting, 9, 0b000_000_000_000_000_000_000_000),
                                player(1, Setting, 9, 0b000_000_000_000_000_000_000_000)
                        ),
                        move(23)
                )
        )
    }

    @Test
    fun toRemoving1() {
        assertEquals(
                mill(
                        player(0, Removing1, 6, 0b000_000_111_000_000_000_000_000),
                        player(1, Setting, 7, 0b000_101_000_000_000_000_000_000)
                ),
                Setting.moved(
                        mill(
                                player(0, Setting, 7, 0b000_000_101_000_000_000_000_000),
                                player(1, Setting, 7, 0b000_101_000_000_000_000_000_000)
                        ),
                        move(16)
                )
        )
    }

    @Test
    fun toRemoving2() {
        assertEquals(
                mill(
                        player(0, Removing2, 4, 0b000_000_000_000_000_010_010_111),
                        player(1, Setting, 5, 0b110_101_000_000_000_000_000_000)
                ),
                Setting.moved(
                        mill(
                                player(0, Setting, 5, 0b000_000_000_000_000_010_010_101),
                                player(1, Setting, 5, 0b110_101_000_000_000_000_000_000)
                        ),
                        move(1)
                )
        )
    }

    @Test
    fun toMoving() {
        assertEquals(
                mill(
                        player(1, Setting, 1, 0b101_101_101_000_000_000_000_000),
                        player(0, Moving, 0, 0b010_000_010_011_000_000_010_011)
                ),
                Setting.moved(
                        mill(
                                player(0, Setting, 1, 0b010_000_010_011_000_000_010_001),
                                player(1, Setting, 1, 0b101_101_101_000_000_000_000_000)
                        ),
                        move(1)
                )
        )
    }

    @Test
    fun moves() {
        assertEquals(
                MoveArray(
                        intArrayOf(
                                0b000_000_000_000_000_000_000_001,
                                0b000_000_000_000_000_000_000_010,
                                0b000_000_000_000_000_000_000_100,

                                0b000_000_000_000_000_000_001_000,
                                0b000_000_000_000_000_000_010_000,
                                0b000_000_000_000_000_000_100_000,

                                0b000_000_000_000_000_001_000_000,
                                0b000_000_000_000_000_010_000_000,
                                0b000_000_000_000_000_100_000_000,

                                0b000_000_000_000_010_000_000_000,
                                0b000_000_000_000_100_000_000_000,

                                0b000_000_000_100_000_000_000_000,

                                0b000_000_100_000_000_000_000_000,

                                0b001_000_000_000_000_000_000_000,
                                0b010_000_000_000_000_000_000_000
                        )
                ),
                Setting.moves(
                        mill(
                                player(0, Setting, 5, 0b000_110_001_000_001_000_000_000),
                                player(1, Setting, 4, 0b100_001_010_011_000_000_000_000)
                        ),
                        MoveArray()
                )
        )
    }
}