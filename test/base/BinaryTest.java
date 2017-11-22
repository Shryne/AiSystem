package base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryTest {
    @Test
    public void setFirst() {
        assertBinary(
                0b10_00000000000000000000000000000000000000000000000000000000000000L,
                Binary.set(0, 2, 0, 2)
        );
    }

    @Test
    public void setLast() {
        assertBinary(
                0b10L,
                Binary.set(0, 2, 31, 2)
        );
    }

    @Test
    public void setMiddle() {
        assertBinary(
                0b10L,
                Binary.set(0, 2, 31, 2)
        );
    }

    @Test
    public void setIncompleteLast() {
        assertBinary(
                0b10L,
                Binary.set(0, 2, 31, 2)
        );
    }

    @Test
    public void setIncompleteMiddle() {
        assertBinary(
                0b10L,
                Binary.set(0, 2, 31, 2)
        );
    }

    /*------------------------------------------------------------------------------------------------------------------
    shortcuts
    ------------------------------------------------------------------------------------------------------------------*/
    private void assertBinary(long expected, long result) {
        if (expected != result) {
            System.out.println("Excepted: " + Long.toBinaryString(expected));
            System.out.println("Result: " + Long.toBinaryString(result));
        }
        assertTrue(expected == result);
    }
}
