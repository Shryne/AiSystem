package base.mill.board

import base.mill.isDoubleMill
import base.mill.move
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class IsDoubleMillTest {
    @Test
    fun empty() {
        assertFalse(
                0b000_000_000_000_000_000_000_100.isDoubleMill(move(2))
        )
    }

    @Test
    fun notAMill() {
        assertFalse(
                0b011_000_000_110_000_100_010_110.isDoubleMill(move(2))
        )
    }

    @Test
    fun millButNotDouble() {
        assertFalse(
                0b100_000_000_000_000_010_010_010.isDoubleMill(move(1))
        )
    }

    @Test
    fun doubleMillButNotCreated() {
        assertFalse(
                0b100_000_000_000_000_010_010_111.isDoubleMill(move(23))
        )
    }


    @Test
    fun doubleMillsCreated() {
        assertTrue(0b001_000_000_000_001_000_000_111.isDoubleMill(move(0)))
        assertTrue(0b000_000_000_000_000_010_010_111.isDoubleMill(move(1)))
        assertTrue(0b100_000_000_100_000_000_000_111.isDoubleMill(move(2)))

        assertTrue(0b000_001_000_000_010_000_111_000.isDoubleMill(move(3)))
        assertTrue(0b000_000_000_000_000_010_111_010.isDoubleMill(move(4)))
        assertTrue(0b000_100_000_010_000_000_111_000.isDoubleMill(move(5)))

        assertTrue(0b000_000_001_000_100_111_000_000.isDoubleMill(move(6)))
        assertTrue(0b000_000_000_000_000_111_010_010.isDoubleMill(move(7)))
        assertTrue(0b000_000_100_001_000_111_000_000.isDoubleMill(move(8)))

        assertTrue(0b001_000_000_000_111_000_000_001.isDoubleMill(move(9)))
        assertTrue(0b000_001_000_000_111_000_001_000.isDoubleMill(move(10)))
        assertTrue(0b000_000_001_000_111_001_000_000.isDoubleMill(move(11)))

        assertTrue(0b000_000_100_111_000_100_000_000.isDoubleMill(move(12)))
        assertTrue(0b000_100_000_111_000_000_100_000.isDoubleMill(move(13)))
        assertTrue(0b100_000_000_111_000_000_000_100.isDoubleMill(move(14)))

        assertTrue(0b000_000_111_000_100_001_000_000.isDoubleMill(move(15)))
        assertTrue(0b010_010_111_000_000_000_000_000.isDoubleMill(move(16)))
        assertTrue(0b000_000_111_001_000_100_000_000.isDoubleMill(move(17)))

        assertTrue(0b000_111_000_000_010_000_001_000.isDoubleMill(move(18)))
        assertTrue(0b010_111_010_000_000_000_000_000.isDoubleMill(move(19)))
        assertTrue(0b000_111_000_010_000_000_100_000.isDoubleMill(move(20)))

        assertTrue(0b111_000_000_000_001_000_000_001.isDoubleMill(move(21)))
        assertTrue(0b111_010_010_000_000_000_000_000.isDoubleMill(move(22)))
        assertTrue(0b111_000_000_100_000_000_000_100.isDoubleMill(move(23)))
    }

    @Test
    fun doubleMillOnNotPureBoard() {
        assertTrue(
                0b011_011_100_001_000_010_010_111.isDoubleMill(move(1))
        )
    }
}