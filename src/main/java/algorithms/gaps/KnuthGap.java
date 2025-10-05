package algorithms.gaps;

public class KnuthGap implements GapSequence {
    @Override
    public int firstGap(int n) {
        if (n <= 1) return 0;
        int h = 1;
        while (h < n / 3) h = 3*h + 1;
        return h;
    }
    @Override
    public int nextGap(int currentGap) {
        if (currentGap <= 1) return 0;
        return (currentGap - 1) / 3;
    }
}