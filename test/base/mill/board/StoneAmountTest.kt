package base.mill.board

import base.mill.move
import base.mill.stoneAmount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StoneAmountTest {
    @Test
    fun empty() {
        assertEquals(0, 0.stoneAmount)
    }

    @Test
    fun oneToFullConsecutively() {
        var board = 0
        for (stoneAmount in 1..24) {
            board = board or move(stoneAmount)
            assertEquals(stoneAmount, board.stoneAmount)
        }
    }

    @Test
    fun multipleMixed() {
        assertEquals(7, 0b001_001_011_000_100_000_101_000.stoneAmount)
    }
}