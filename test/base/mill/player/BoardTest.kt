package base.mill.player

import base.mill.board
import base.mill.player
import base.mill.stage.Moving
import base.mill.stage.Removing
import base.mill.stage.Setting
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * - mutable
 *
 * @param
 * @return
 */
class BoardTest {
    @Test
    fun empty() {
        assertEquals(
                0b000_000_000_000_000_000_000_000,
                player(0, Setting, 1, 0b000_000_000_000_000_000_000_000).board
        )
    }

    @Test
    fun first() {
        assertEquals(
                0b000_000_000_000_000_000_000_001,
                player(1, Setting, 1, 0b000_000_000_000_000_000_000_001).board
        )
    }

    @Test
    fun last() {
        assertEquals(
                0b100_000_000_000_000_000_000_000,
                player(0, Setting, 1, 0b100_000_000_000_000_000_000_000).board
        )
    }

    @Test
    fun stoneToSetAlmostFull() {
        assertEquals(
                0b011_111_111_111_111_111_111_111,
                player(1, Setting, 1, 0b011_111_111_111_111_111_111_111).board
        )
    }

    @Test
    fun full() {
        assertEquals(
                0b111_111_111_111_111_111_111_111,
                player(0, Setting, 1, 0b111_111_111_111_111_111_111_111).board
        )
    }

    @Test
    fun movingFull() {
        assertEquals(
                0b111_111_111_111_111_111_111_111,
                player(1, Moving, 0, 0b111_111_111_111_111_111_111_111).board
        )
    }

    @Test
    fun removing1Empty() {
        assertEquals(
                0b000_000_000_000_000_000_000_000,
                player(0, Removing, 0, 0b000_000_000_000_000_000_000_000).board
        )
    }

    @Test
    fun stonesToSetEmpty() {
        assertEquals(
                0b000_000_000_000_000_000_000_000,
                player(1, Setting, 7, 0b000_000_000_000_000_000_000_000).board
        )
    }
}