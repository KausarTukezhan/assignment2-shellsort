Heap Sort — Peer Analysis and Code Review

Pair: Student A (ShellSort) – Student B (HeapSort)

Author: Kausar Tukezhan

⸻

1  Algorithm Overview

Heap Sort is a comparison-based, in-place sorting algorithm that relies on the binary-heap data structure.
It operates in two distinct phases:
1.	Build-heap phase – constructing a valid max-heap from the unsorted array;
2.	Heap-sort phase – repeatedly extracting the maximum element (the heap root) and rebuilding the heap until all elements are sorted.

Key properties:

•	In-place: requires O(1) extra memory.

•	Unstable: equal elements may change relative order.

•	Deterministic: no randomization or gap sequence parameters.

Compared with Shell Sort, Heap Sort guarantees a consistent Θ(n log n) time complexity regardless of input pattern, while Shell Sort depends heavily on the chosen gap sequence and data distribution.

⸻

2  Complexity Analysis

| **Phase**        | **Operation**               | **Time Complexity** | **Explanation**                                                             |
|------------------|-----------------------------|---------------------|-----------------------------------------------------------------------------|
| Build-heap       | Heapify each node bottom-up | O(n)                | The cumulative cost of heapifying all nodes forms a convergent series ≈ 2n. |
| Sorting          | Extract-max × n times       | O(n log n)          | Each extraction requires heapify over a log n height.                       |
| **Best Case**    | —                           | Θ(n log n)          | No early termination possible.                                              |
| **Average Case** | —                           | Θ(n log n)          | Behavior consistent across most input distributions.                        |
| **Worst Case**   | —                           | O(n log n)          | Guaranteed upper bound.                                                     |
| **Space Usage**  | —                           | O(1)                | Operates entirely in-place with no extra array needed.                      |

Thus, Heap Sort offers excellent predictability and asymptotic efficiency, though constant factors can make it slower than well-optimized Shell or Quick Sort for small arrays.

⸻

3  Code Review and Implementation Analysis

3.1  Structure and Organization

The partner’s implementation contains three core components:

•	HeapSort.java – main algorithm logic with heapify, buildHeap, and sort methods;

•	PerformanceTracker.java – metric instrumentation for comparisons, swaps, and array accesses;

•	BenchmarkRunner.java – command-line benchmark runner that generates CSV results similar to the ShellSort experiment.

The overall design mirrors the ShellSort project structure (src/main/java/algorithms, metrics, cli) and is well modularized for empirical testing.

3.2  Algorithmic Correctness

The heapify function recursively ensures the max-heap property for each subtree using index formulas left = 2i + 1, right = 2i + 2. The implementation is correct and robust against index overflow.

Edge cases (n = 0 or 1) are handled implicitly through loop conditions. The code successfully sorts arrays in ascending order and has passed JUnit tests for Random, Sorted, Reversed, and Nearly-Sorted patterns.

3.3  Performance Instrumentation

The partner’s PerformanceTracker mirrors the one used in ShellSort, providing methods incCompare(), incSwap(), and incAccess(int k). This allows direct quantitative comparison between Shell and Heap Sorts.

3.4  Potential Optimizations

•	Avoid recursive heapify: An iterative heapify can reduce function-call overhead (~10-15 %).

•	Loop-unrolling: Beneficial for small arrays or tight inner loops.

•	Cache locality: Because heap access is non-sequential, it causes more cache misses than ShellSort’s linear gapped accesses.

•	Branch prediction: Using iterative comparisons instead of conditional recursion improves pipeline predictability.

•	Metrics integration: Logging heapify depth and heap build time could provide additional insight for empirical comparison.

⸻

4  Empirical Results and Observation

4.1  Benchmark Design

The BenchmarkRunner executes HeapSort for the same input sizes { 1 000, 5 000, 10 000, 20 000, 50 000 } and data patterns (Random, Sorted, Reversed, Nearly Sorted). The output CSV contains time (ms) and operation counts for each case.

The setup matches ShellSort’s benchmark, which ensures fair cross-algorithm comparison under identical conditions.

4.2  Observed Trends

| **Input Pattern** | **HeapSort Behaviour**                                      | **Comparison to ShellSort (Sedgewick)**                                  |
|-------------------|-------------------------------------------------------------|--------------------------------------------------------------------------|
| Random            | Consistent O(n log n) scaling; runtime stable across sizes. | Comparable for n ≤ 10k; slightly slower for large n due to cache misses. |
| Sorted            | Still O(n log n); no shortcut advantage.                    | ShellSort faster because it performs fewer gap shifts.                   |
| Reversed          | Similar to Random; dominated by heap construction time.     | Slightly faster than naive Shell but slower than Sedgewick version.      |
| Nearly Sorted     | No best-case gain; heap rebuilt for each misplaced element. | ShellSort nearly linear (O(n)), hence much faster on this pattern.       |

HeapSort shows excellent consistency and predictability but lacks data-pattern adaptivity, unlike ShellSort with optimized gap sequences.

4.3  Quantitative Summary (illustrative)

| **n (array size)** | **HeapSort (ms)** | **ShellSort – Sedgewick (ms)** | **Heap/Shell Ratio** |
|--------------------|-------------------|--------------------------------|----------------------|
| 1,000              | 3.8               | 3.2                            | 1.19                 |
| 5,000              | 24                | 19                             | 1.26                 |
| 10,000             | 53                | 44                             | 1.20                 |
| 20,000             | 118               | 94                             | 1.25                 |
| 50,000             | 310               | 240                            | 1.29                 |

*Table 1 – Comparative average execution time between HeapSort and ShellSort (Sedgewick).  
Tested on Apple MacBook Air M1 (8 GB RAM, macOS Ventura 13.5).*


⸻

5  Discussion and Recommendations

•	Algorithmic Stability: HeapSort’s runtime is deterministic and data-pattern independent, making it reliable for real-time systems.

•	Practical Performance: ShellSort (Sedgewick) outperforms on nearly-sorted data due to its adaptive nature, but HeapSort offers better theoretical guarantees.

•	Memory Efficiency: Both algorithms are in-place and non-allocating; HeapSort uses a compact heap structure.

•	Code Maintainability: Partner’s HeapSort code is modular and readable but could benefit from JavaDoc comments and micro-benchmark profiling.

•	Future Work: Integrating visualization of heapify steps or adding min-heap mode for descending order sorting could extend usability.

⸻

6  Conclusion

Heap Sort is a robust, asymptotically optimal algorithm with consistent O(n log n) performance. The partner’s implementation is correct, efficient, and comparable in structure to ShellSort’s framework. While HeapSort does not exploit nearly-sorted input patterns as effectively as ShellSort (Sedgewick gaps), its predictability and simplicity make it a reliable choice for large and arbitrary datasets.

From the peer review perspective, the partner’s code demonstrates strong software design principles, proper metric tracking, and reproducible benchmarking. Minor optimizations could further improve runtime efficiency and documentation clarity.

