package base.mill.board

import base.mill.move
import base.mill.isMill
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class IsMillTest {
    @Test
    fun empty() {
        assertFalse(
                0b000_000_000_000_000_000_000_010.isMill(move(1))
        )
    }

    @Test
    fun notAMill() {
        assertFalse(
                0b011_000_000_110_000_100_010_110.isMill(move(2))
        )
    }

    @Test
    fun millButNotCreated() {
        assertFalse(
                0b000_111_000_000_000_001_000_000.isMill(move(6))
        )
    }


    @Test
    fun horizontalMillsCreated() {
        assertTrue(0b000_000_000_000_000_000_000_111.isMill(move(0)))
        assertTrue(0b000_000_000_000_000_000_111_000.isMill(move(4)))
        assertTrue(0b000_000_000_000_000_111_000_000.isMill(move(7)))
        assertTrue(0b000_000_000_000_111_000_000_000.isMill(move(10)))
        assertTrue(0b000_000_000_111_000_000_000_000.isMill(move(13)))
        assertTrue(0b000_000_111_000_000_000_000_000.isMill(move(15)))
        assertTrue(0b000_111_000_000_000_000_000_000.isMill(move(19)))
        assertTrue(0b111_000_000_000_000_000_000_000.isMill(move(23)))
    }

    @Test
    fun verticalMillsCreated() {
        assertTrue(0b001_000_000_000_001_000_000_001.isMill(move(9)))
        assertTrue(0b000_001_000_000_010_000_001_000.isMill(move(3)))
        assertTrue(0b000_000_001_000_100_001_000_000.isMill(move(15)))
        assertTrue(0b000_000_000_000_000_010_010_010.isMill(move(7)))
        assertTrue(0b010_010_010_000_000_000_000_000.isMill(move(16)))
        assertTrue(0b000_000_100_001_000_100_000_000.isMill(move(12)))
        assertTrue(0b000_100_000_010_000_000_100_000.isMill(move(13)))
        assertTrue(0b100_000_000_100_000_000_000_100.isMill(move(23)))
    }

    @Test
    fun doubleMill() {
        assertTrue(
                0b000_000_000_000_000_010_010_111.isMill(move(1))
        )
    }

    @Test
    fun millOnNotPureBoard() {
        assertTrue(
                0b011_011_100_001_000_010_010_010.isMill(move(1))
        )
    }
}