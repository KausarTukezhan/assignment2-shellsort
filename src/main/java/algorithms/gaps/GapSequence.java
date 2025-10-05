package algorithms.gaps;

public interface GapSequence {
    int firstGap(int n);
    int nextGap(int currentGap);
    default String name() { return getClass().getSimpleName().replace("Gap",""); }
}