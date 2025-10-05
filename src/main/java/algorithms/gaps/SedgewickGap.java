package algorithms.gaps;

public class SedgewickGap implements GapSequence {
    private static final int[] GAPS_ASC = {
            1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905,
            8929, 16001, 36289, 64769, 146305, 260609,
            587521, 1045505, 2354689, 4188161
    };

    @Override
    public int firstGap(int n) {
        if (n <= 1) return 0;
        int prev = 1;
        for (int g : GAPS_ASC) {
            if (g >= n) break;
            prev = g;
        }
        return prev;
    }
    @Override
    public int nextGap(int currentGap) {
        if (currentGap == 1) return 0;
        int prev = 1;
        for (int g : GAPS_ASC) {
            if (g >= currentGap) break;
            prev = g;
        }
        return prev;
    }
}
