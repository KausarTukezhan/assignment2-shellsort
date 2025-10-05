package cli;

import algorithms.ShellSort;
import algorithms.gaps.GapSequence;
import algorithms.gaps.KnuthGap;
import algorithms.gaps.SedgewickGap;
import algorithms.gaps.ShellGap;
import metrics.PerformanceTracker;
import util.ArrayGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        int[] sizes = {1000, 5000, 10000, 20000, 50000};
        ArrayGenerator.Pattern[] patterns = {
                ArrayGenerator.Pattern.RANDOM,
                ArrayGenerator.Pattern.SORTED,
                ArrayGenerator.Pattern.REVERSED,
                ArrayGenerator.Pattern.NEARLY_SORTED
        };

        GapSequence[] gaps = {
                new ShellGap(),
                new KnuthGap(),
                new SedgewickGap()
        };

        Path outDir = Paths.get(System.getProperty("user.dir"), "docs", "performance-plots");
        Files.createDirectories(outDir);
        Path outFile = outDir.resolve("shellsort_results.csv");

        ArrayGenerator gen = new ArrayGenerator(42L);

        try (FileWriter csv = new FileWriter(outFile.toFile())) {
            csv.write("Gap,Pattern,Size,Time(ms),Comparisons,Swaps,ArrayAccesses\n");

            for (GapSequence gap : gaps) {
                for (ArrayGenerator.Pattern p : patterns) {
                    for (int n : sizes) {
                        int[] a = gen.generate(n, p);
                        PerformanceTracker t = new PerformanceTracker();
                        ShellSort sorter = new ShellSort(gap, t);

                        long start = System.currentTimeMillis();
                        sorter.sort(a);
                        long end = System.currentTimeMillis();
                        long time = end - start;

                        csv.write(String.format("%s,%s,%d,%d,%d,%d,%d%n",
                                gap.name(), p, n, time,
                                t.getComparisons(), t.getSwaps(), t.getArrayAccesses()));

                        System.out.printf("✔ %s | %-14s | n=%-6d → %4d ms%n",
                                gap.name(), p, n, time);
                    }
                }
            }
        }

        System.out.println("\nBenchmark completed successfully!");
        System.out.println("CSV file saved to: " + outFile.toAbsolutePath());
    }
}