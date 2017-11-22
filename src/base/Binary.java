package base;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a utility class for games that use primitives to represent their state. Because of that,
 * they need bit shift operations which can be found here.
 */
public final class Binary {
    private Binary() {}

    /**
     * Sets the given number in the given position of the board.
     * @param board The game board (or abstractly speaking, some number).
     * @param value The value to be set. This value has to be small enough to fit in the given amount of bits per tile.
     * @param pos The position that will contain the number.
     * @param bitsPerTile The amount of bits for a tile in the board. Example: A long has 64 bits. If bitsPerTile equals
     *                    2, the board can contain up to 32 tiles.
     * @return The board with the value on the given position.
     */
    public static long set(long board, int value, int pos, int bitsPerTile) {
        return board ^ (value << (Long.SIZE - pos - 1) * bitsPerTile);
    }

    public static int get(long board, int pos, int bitsPerTile) {
        return (int) (board >> (bitsPerTile * pos) << (Long.SIZE - bitsPerTile) >>> (Long.SIZE - bitsPerTile));
    }

    public static List<Integer> toList(long board, int tilesPerBoard, int bitsPerTile) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tilesPerBoard; i++) {
            result.add(get(board, i, bitsPerTile));
        }
        return result;
    }

    public static Long toBoard(List<Integer> list, int tilesPerBoard, int bitsPerTile) {
        long result = 0;
        for (int i = 0; i < tilesPerBoard; i++) {
            result = set(result, list.get(i), i, bitsPerTile);
        }
        return result;
    }
}
