package base._2048;

import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static utils.MathKt.randomFast;

/**
 * This class is written in java, because it's impossible to write the long literals in kotlin without getting a
 * compile time error.
 *
 * 4.8M runs per second with random agent (Moves per second, Avg moves per second active)
 */
public final class Game2048Binary implements Game2048<Long> {
    /*------------------------------------------------------------------------------------------------------------------
    static stuff
    ------------------------------------------------------------------------------------------------------------------*/
    private static final int DEFAULT_MAP_ROW_LENGTH = 4;
    private static final int DEFAULT_MAP_SIZE = DEFAULT_MAP_ROW_LENGTH * DEFAULT_MAP_ROW_LENGTH;

    private static final int BITS_PER_TILE = 4;
    private static final long ROW_MASK = 0xFFFFL;
    private static final long COL_MASK = 0x000F000F000F000FL;

    private static final long ROW_LEFT_TABLE[] = new long[65536];
    private static final long ROW_RIGHT_TABLE[] = new long[65536];
    private static final long COL_UP_TABLE[] = new long[65536];
    private static final long COL_DOWN_TABLE[] = new long[65536];
    private static final int SCORE_TABLE[] = new int[65536];

    static {
        for (int row = 0; row < 65536; ++row) {
            // create all possible lines, from 0000 to 15-15-15-15 (32k - 32k - 32k - 32k)
            long line[] = {
                    row & 0xfL,
                    (row >> 4) & 0xFL,
                    (row >> 8) & 0xFL,
                    (row >> 12) & 0xFL
            };

            // calculate the score of the current board based on the tiles and all previous tiles necessary to merge
            // up to it
            int score = 0;
            for (int i = 0; i < 4; ++i) {
                long rank = line[i];
                if (rank >= 2) {
                    // the score is the total sum of the tile and all intermediate merged tiles
                    score += (rank - 1) * (1 << rank);
                }
            }
            SCORE_TABLE[row] = score;

            // execute a move to the left
            for (int i = 0; i < 3; ++i) {
                int j;
                for (j = i + 1; j < 4; ++j) {
                    if (line[j] != 0) break;
                }
                if (j == 4) break; // no more tiles to the right

                if (line[i] == 0) {
                    line[i] = line[j];
                    line[j] = 0;
                    i--; // retry this entry
                } else if (line[i] == line[j] && line[i] != 0xFL) {
                    line[i]++;
                    line[j] = 0;
                }
            }

            long result = line[0] | (line[1] << 4) | (line[2] << 8) | (line[3] << 12);


            long rev_result = reverseRow((int) result);
            long rev_row = reverseRow(row);

            ROW_LEFT_TABLE[row] = row ^ result;
            ROW_RIGHT_TABLE[(int) rev_row] = rev_row ^ rev_result;
            COL_UP_TABLE[row] = unpackCol(row) ^ unpackCol(result);
            COL_DOWN_TABLE[(int) rev_row] = unpackCol(rev_row) ^ unpackCol(rev_result);
        }
    }
    /*------------------------------------------------------------------------------------------------------------------
    static stuff
    ------------------------------------------------------------------------------------------------------------------*/
    private long board = 0;
    private int initialScore = 0;

    private PossibleMovesArray possibleMoves = new PossibleMovesArray();
    private boolean possibleMovesDetermined = false; // for the current turn
    /*------------------------------------------------------------------------------------------------------------------
    public static methods
    ------------------------------------------------------------------------------------------------------------------*/
    public Game2048Binary() {
        restart();
    }

    @Override
    public void progress(@NotNull Move2048 move) {
        assert !has(board, 15);
        assert possibleMoves.contains(move);

        long backup = board;
        switch (move) {
            case LEFT: board = executeMoveLeft(backup); break;
            case RIGHT: board = executeMoveRight(backup); break;
            case UP: board = executeMoveUp(backup); break;
            case DOWN: board = executeMoveDown(backup); break;
            default: throw new IllegalArgumentException("Move: " + move + " not available.");
        }

        if (board == backup) {
            System.out.println(possibleMoves);
            System.out.println(move);
            System.out.println(this);
        }

        assert board != backup;
        board = insertTileRand(board, randomNumber(), countEmpty(board));
        possibleMovesDetermined = false;
    }

    @Override
    public boolean isOver() {
        return executeMoveUp(board) == board && executeMoveLeft(board) == board &&
                executeMoveRight(board) == board && executeMoveDown(board) == board;
    }

    @Override
    public void restart() {
        board = insertTileRand(
                insertTileRand(0, randomNumber(), 16),
                randomNumber(),
                15
        );
        initialScore = getScore();
        determinePossibleMoves();
    }

    @NotNull
    @Override
    public ImmutablePossibleMovesArray<Move2048> getPossibleMoves() {
        if (!possibleMovesDetermined) determinePossibleMoves();
        return possibleMoves;
    }

    public Long getBinaryMap() {
        return board;
    }

    public void setBinaryMap(Long map) {
        board = map;
        determinePossibleMoves();
    }

    @NotNull
    @Override
    public Long getMap() {
        return board;
    }

    @Override
    public int getScore() {
        return scoreHelper(board) - initialScore;
    }

    @Override
    public int getHighestTile() {
        int highestTile = 0;
        for (int i = 0; i < DEFAULT_MAP_SIZE; i++) {
            highestTile = Math.max(highestTile, get(board, i));
        }
        return pow(2, highestTile);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[score: ").append(getScore()).append("]\n[");
        for (int i = 0; i < DEFAULT_MAP_SIZE; i++) {
            result.append(get(board, i));

            if ((i + 1) % DEFAULT_MAP_ROW_LENGTH == 0) result.append("]\n[");
            else result.append(", ");
        }
        return result.deleteCharAt(result.length() - 1).deleteCharAt(result.length() - 1).toString();
    }
    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private int countEmpty(long board) {
        board |= (board >> 2) & 0x3333333333333333L;
        board |= (board >> 1);
        board = ~board & 0x1111111111111111L;
        // At this point each nibble is:
        //  0 if the original nibble was non-zero
        //  1 if the original nibble was zero
        // Next sum them all
        board += board >> 32;
        board += board >> 16;
        board += board >> 8;
        board += board >> 4; // this can overflow to the next nibble if there were 16 empty positions
        return (int) (board & 0xf);
    }

    private static long transpose(long x) {
        long a1 = x & 0xF0F00F0FF0F00F0FL;
        long a2 = x & 0x0000F0F00000F0F0L;
        long a3 = x & 0x0F0F00000F0F0000L;
        long a = a1 | (a2 << 12) | (a3 >> 12);
        long b1 = a & 0xFF00FF0000FF00FFL;
        long b2 = a & 0x00FF00FF00000000L;
        long b3 = a & 0x00000000FF00FF00L;
        return b1 | (b2 >> 24) | (b3 << 24);
    }

    public static long executeMoveDown(long board) {
        long ret = board;
        long t = transpose(board);
        ret ^= COL_UP_TABLE[(int) (t & ROW_MASK)];
        ret ^= COL_UP_TABLE[(int) ((t >> 16) & ROW_MASK)] <<  4;
        ret ^= COL_UP_TABLE[(int) ((t >> 32) & ROW_MASK)] <<  8;
        ret ^= COL_UP_TABLE[(int) ((t >> 48) & ROW_MASK)] << 12;
        return ret;
    }

    private static long executeMoveUp(long board) {
        long ret = board;
        long t = transpose(board);
        ret ^= COL_DOWN_TABLE[(int) ((t) & ROW_MASK)];
        ret ^= COL_DOWN_TABLE[(int) ((t >> 16) & ROW_MASK)] <<  4;
        ret ^= COL_DOWN_TABLE[(int) ((t >> 32) & ROW_MASK)] <<  8;
        ret ^= COL_DOWN_TABLE[(int) ((t >> 48) & ROW_MASK)] << 12;
        return ret;
    }

    private static long executeMoveRight(long board) {
        long ret = board;
        ret ^= ROW_LEFT_TABLE[(int) ((board) & ROW_MASK)];
        ret ^= ROW_LEFT_TABLE[(int) ((board >> 16) & ROW_MASK)] << 16;
        ret ^= ROW_LEFT_TABLE[(int) ((board >> 32) & ROW_MASK)] << 32;
        ret ^= ROW_LEFT_TABLE[(int) ((board >> 48) & ROW_MASK)] << 48;
        return ret;
    }

    private static long executeMoveLeft(long board) {
        long ret = board;
        ret ^= ROW_RIGHT_TABLE[(int) (board & ROW_MASK)];
        ret ^= ROW_RIGHT_TABLE[(int) ((board >> 16) & ROW_MASK)] << 16;
        ret ^= ROW_RIGHT_TABLE[(int) ((board >> 32) & ROW_MASK)] << 32;
        ret ^= ROW_RIGHT_TABLE[(int) ((board >> 48) & ROW_MASK)] << 48;
        return ret;
    }

    private static int scoreHelper(long board) {
        return SCORE_TABLE[(int) (board & ROW_MASK)] +
                SCORE_TABLE[(int) ((board >> 16) & ROW_MASK)] +
                SCORE_TABLE[(int) ((board >> 32) & ROW_MASK)] +
                SCORE_TABLE[(int) ((board >> 48) & ROW_MASK)];
    }

    private static long insertTileRand(long board, int number, int amountOfEmpties) {
        int emptySpawnIndex = randomFast(amountOfEmpties);
        int emptiesCounter = -1;
        int spawnIndex = -1;

        while (emptiesCounter < emptySpawnIndex) {
            spawnIndex++;
            if (get(board, spawnIndex) == 0) emptiesCounter++;
        }

        return set(board, spawnIndex, number);
    }

    private static int get(long board, int index) {
        return (int) (
                board >> (BITS_PER_TILE * (DEFAULT_MAP_SIZE - index - 1))
                        << (Long.SIZE - BITS_PER_TILE) >>> (Long.SIZE - BITS_PER_TILE)
        );
    }

    private static long set(long board, int index, long value) {
        return board ^ (value << ((DEFAULT_MAP_SIZE - index - 1) * BITS_PER_TILE));
    }

    private static int randomNumber() {
        return (randomFast(10) < 9) ? 1 : 2;
    }

    private static boolean has(long board, @SuppressWarnings("SameParameterValue") int value) {
        for (int i = 0; i < DEFAULT_MAP_SIZE; i++) {
            if (get(board, i) == value) return true;
        }
        return false;
    }
    /*------------------------------------------------------------------------------------------------------------------
    private static helper
    ------------------------------------------------------------------------------------------------------------------*/
    private static long unpackCol(long row) {
        return (row | (row << 12L) | (row << 24L) | (row << 36L)) & COL_MASK;
    }

    private static int reverseRow(int row) {
        return Short.toUnsignedInt((short) ((row >> 12) | ((row >> 4) & 0x00F0) | ((row << 4) & 0x0F00) | (row << 12)));
    }

    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private void determinePossibleMoves() {
        int possibleMovesAmount = 0;
        if (executeMoveLeft(board) != board) {
            possibleMoves.set(possibleMovesAmount, Move2048.LEFT);
            possibleMovesAmount++;
        }

        if (executeMoveRight(board) != board) {
            possibleMoves.set(possibleMovesAmount, Move2048.RIGHT);
            possibleMovesAmount++;
        }

        if (executeMoveUp(board) != board) {
            possibleMoves.set(possibleMovesAmount, Move2048.UP);
            possibleMovesAmount++;
        }

        if (executeMoveDown(board) != board) {
            possibleMoves.set(possibleMovesAmount, Move2048.DOWN);
        }
        possibleMovesDetermined = true;
    }

    // source: http://stackoverflow.com/a/10517609/7563350
    private int pow(int base, int exp) {
        int result = 1;
        while (exp != 0) {
            if ((exp & 1) == 1) result *= base;
            exp >>= 1;
            base *= base;
        }

        return result;
    }
}
