package base.mill.mill

import base.mill.mill
import base.mill.player
import base.mill.stage.Setting
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MillTest {
    @Test
    fun initialZeroFront() {
        assertEquals(
                0b0_000_1001_000_000_000_000_000_000_000_000___1_000_1001_000_000_000_000_000_000_000_000,
                mill(
                        player(0, Setting, 9, 0b000_000_000_000_000_000_000_000),
                        player(1, Setting, 9, 0b000_000_000_000_000_000_000_000)
                )
        )
    }

    @Test
    fun initialOneFront() {
        /*
        Not testable because kotlin doesn't support such values as a literal :(
        assertEquals(
                0b1_000_0101_000_000_000_000_000_000_000_000___0_000_0101_000_000_000_000_000_000_000_000,
                mill(
                        player(1, Setting, 9, 0b000_000_000_000_000_000_000_000),
                        player(0, Setting, 9, 0b000_000_000_000_000_000_000_000)
                )
        )
        */
    }
}