package algorithms;

import algorithms.gaps.GapSequence;
import metrics.PerformanceTracker;

public class ShellSort {
    private final GapSequence gaps;
    private final PerformanceTracker trk;

    public ShellSort(GapSequence gaps, PerformanceTracker tracker) {
        this.gaps = gaps;
        this.trk = tracker;
    }

    public void sort(int[] a) {
        int n = a.length;
        int gap = gaps.firstGap(n);
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int tmp = a[i]; trk.incAccess(1);
                int j = i;
                while (j >= gap) {
                    trk.incCompare();
                    trk.incAccess(2);
                    if (a[j - gap] <= tmp) break;
                    a[j] = a[j - gap]; trk.incAccess(1);
                    trk.incSwap();
                    j -= gap;
                }
                a[j] = tmp; trk.incAccess(1);
            }
            gap = gaps.nextGap(gap);
        }
    }

    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i-1] > a[i]) return false;
        return true;
    }
}