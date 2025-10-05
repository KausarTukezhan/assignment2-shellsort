package algorithms;

import algorithms.gaps.SedgewickGap;
import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import util.ArrayGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShellSortTest {

    private void run(ArrayGenerator.Pattern p) {
        ArrayGenerator gen = new ArrayGenerator(123);
        int[] sizes = {0, 1, 2, 10, 100, 1000};
        for (int n : sizes) {
            int[] a = gen.generate(n, p);
            ShellSort s = new ShellSort(new SedgewickGap(), new PerformanceTracker());
            s.sort(a);
            assertTrue(ShellSort.isSorted(a), "Not sorted: "+p+" n="+n);
        }
    }

    @Test void random()        { run(ArrayGenerator.Pattern.RANDOM); }
    @Test void sorted()        { run(ArrayGenerator.Pattern.SORTED); }
    @Test void reversed()      { run(ArrayGenerator.Pattern.REVERSED); }
    @Test void nearly_sorted() { run(ArrayGenerator.Pattern.NEARLY_SORTED); }
}
