package logic.agent.simulation_game_dependent;

/**
 *
 */
public final class BoardOrdering {
    private BoardOrdering() {}

    public static long upLeftOrder(long board) {
        long res = board & 0xFFFF_0000_FFFF_0000L;
        res |= lineSwitch((board & 0x0000_FFFF_0000_0000L) >>> 32) << 32;
        res |= lineSwitch(board & 0xFFFFL);
        return res;
    }

    /*------------------------------------------------------------------------------------------------------------------
    private helper
    ------------------------------------------------------------------------------------------------------------------*/
    private static long lineSwitch(long line) {
        long res = (line & 0x000FL) << 12;
        res |= ((line >>> 4) & 0x00FL) << 8;
        res |= ((line >>> 8) & 0x0FL) << 4;
        res |= (line >>> 12);
        return res;
    }
}
