package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;

    public void incCompare()     { comparisons++; }
    public void incSwap()        { swaps++; }
    public void incAccess(int k) { arrayAccesses += k; }

    public long getComparisons()  { return comparisons; }
    public long getSwaps()        { return swaps; }
    public long getArrayAccesses(){ return arrayAccesses; }
}