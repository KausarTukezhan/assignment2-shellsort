package algorithms.gaps;

public class ShellGap implements GapSequence {
    @Override
    public int firstGap(int n) {
        if (n <= 1) return 0;
        return n / 2;
    }
    @Override
    public int nextGap(int currentGap) {
        if (currentGap <= 1) return 0;
        return currentGap / 2;
    }
}