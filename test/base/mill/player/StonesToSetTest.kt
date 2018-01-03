package base.mill.player

import base.mill.player
import base.mill.stage.Moving
import base.mill.stage.Setting
import base.mill.stonesToSet
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StonesToSetTest {
    @Test
    fun zero() {
        assertEquals(
                0,
                player(0, Moving, 0, 0b000_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun one() {
        assertEquals(
                1,
                player(1, Setting, 1, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun two() {
        assertEquals(
                2,
                player(0, Setting, 2, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun three() {
        assertEquals(
                3,
                player(1, Setting, 3, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun four() {
        assertEquals(
                4,
                player(0, Setting, 4, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun five() {
        assertEquals(
                5,
                player(1, Setting, 5, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun six() {
        assertEquals(
                6,
                player(0, Setting, 6, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun seven() {
        assertEquals(
                7,
                player(1, Setting, 7, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun eight() {
        assertEquals(
                8,
                player(0, Setting, 8, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }

    @Test
    fun nine() {
        assertEquals(
                9,
                player(1, Setting, 9, 0b011_001_101_010_110_111_001_011).stonesToSet
        )
    }
}