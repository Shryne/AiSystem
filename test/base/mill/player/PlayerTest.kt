package base.mill.player

import base.mill.player
import base.mill.stage.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun settingStageOneStoneToSet() {
        assertEquals(
                0b0_000_0001__000_000_000_000_000_000_000_000,
                player(0, Setting, 1, 0)
        )
    }

    @Test
    fun settingStageNineStonesToSet() {
        assertEquals(
                // toInt is necessary because kotlin has problems with overflowing literals.
                0b1_000_1001__000_000_000_000_000_000_000_000.toInt(),
                player(1, Setting, 9, 0)
        )
    }

    @Test
    fun removing1() {
        assertEquals(
                0b0_011_0000__000_000_000_000_000_000_000_000,
                player(0, Removing1, 0, 0b000_000_000_000_000_000_000_000)
        )
    }

    @Test
    fun moving() {
        assertEquals(
                0b1_001_0000__000_000_000_000_000_000_000_000.toInt(),
                player(1, Moving, 0, 0b000_000_000_000_000_000_000_000)
        )
    }

    @Test
    fun jumping() {
        assertEquals(
                0b0_010_0000__000_000_000_000_000_000_000_000,
                player(0, Jumping, 0, 0b000_000_000_000_000_000_000_000)
        )
    }

    @Test
    fun removing2() {
        assertEquals(
                0b1_100_0000__000_000_000_000_000_000_000_000.toInt(),
                player(1, Removing2, 0, 0b000_000_000_000_000_000_000_000)
        )
    }

    @Test
    fun fullBoard() {
        assertEquals(
                0b1_001_0000__111_111_111_111_111_111_111_111.toInt(),
                player(1, Moving, 0, 0b111_111_111_111_111_111_111_111)
        )
    }

    @Test
    fun settingOneStoneToSetAlmostFullBoard() {
        assertEquals(
                0b0_000_0001__011_111_111_111_111_111_111_111,
                player(0, Setting, 1, 0b011_111_111_111_111_111_111_111)
        )
    }
}