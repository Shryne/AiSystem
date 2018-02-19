package base.mill.player

import base.mill.player
import base.mill.stage
import base.mill.stage.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StageTest {
    @Test
    fun setting() {
        assertEquals(
                Setting,
                player(0, Setting, 3, 0b000_000_000_000_000_000_000_000).stage
        )
    }

    @Test
    fun moving() {
        assertEquals(
                Moving,
                player(1, Moving, 0, 0b000_000_000_000_000_000_000_000).stage
        )
    }

    @Test
    fun jumping() {
        assertEquals(
                Jumping,
                player(0, Jumping, 0, 0b000_000_000_000_000_000_000_000).stage
        )
    }

    @Test
    fun removing() {
        assertEquals(
                Removing,
                player(1, Removing, 1, 0b000_000_000_000_000_000_000_000).stage
        )
    }
}