package base.mill.mill

import base.mill.*
import base.mill.stage.Setting
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovedTest {
    @Test
    fun firstMove() {
        assertEquals(
                mill(
                        player(0, Setting, 9, 0),
                        player(1, Setting, 9, 0)
                ).moved(move(1)),
                mill(
                        player(1, Setting, 9, 0),
                        player(0, Setting, 8, move(1))
                )
        )
    }

    @Test
    fun secondMove() {
        assertEquals(
                mill(
                        player(0, Setting, 9, 0),
                        player(1, Setting, 8, move(1))
                ).moved(move(2)),
                mill(
                        player(1, Setting, 8, move(1)),
                        player(0, Setting, 8, move(2))
                )
        )
    }

    @Test
    fun settingToMoving() {

    }


    @Test
    fun settingToRemoving() {

    }

    @Test
    fun settingToRemoving2() {

    }

    @Test
    fun movingToRemoving() {

    }

    @Test
    fun removingP1MovingToJumping() {

    }

    @Test
    fun jumpingToRemoving() {

    }

    @Test
    fun removingToSetting() {

    }

    @Test
    fun removingToMoving() {

    }

    @Test
    fun removingToJumping() {

    }

    @Test
    fun movingToEnd() {

    }
}
