# Benchmark Notes — Shell Sort (Gap Sequences: Shell, Knuth, Sedgewick)

**Author:** Kausar Tukezhan  

---

## 1. Objective
This benchmark evaluates the empirical performance of the **Shell Sort algorithm** using three different **gap sequences** — Shell’s, Knuth’s, and Sedgewick’s — across various input array patterns.  
The collected data are used to validate theoretical time complexities and visualize practical performance trends.

---

## 2. Experimental Setup

| Parameter | Description |
|------------|-------------|
| **Programming Language** | Java 17 |
| **Build Tool** | Maven 3.9 |
| **Benchmark Class** | `cli.BenchmarkRunner` |
| **Metrics Collected** | Execution time (ms), comparisons, swaps, array accesses |
| **Array Generator** | `util.ArrayGenerator` |
| **Random Seed** | `42L` (fixed for reproducibility) |
| **Input Sizes (n)** | 1 000; 5 000; 10 000; 20 000; 50 000 |
| **Input Patterns** | `RANDOM`, `SORTED`, `REVERSED`, `NEARLY_SORTED` |
| **Gap Sequences Tested** | `SHELL`, `KNUTH`, `SEDGEWICK` |
| **Repetitions per Test** | 1 (single pass; can be extended for averaging) |
| **Output File** | `docs/performance-plots/shellsort_results.csv` |
| **Graphing Tool** | Google Colab (Python 3, Matplotlib) |

---

## 3. Execution Procedure

### 3.1 Build and Run
From the project root directory:

```bash
# Compile and package
mvn clean package -DskipTests

# Run benchmark
java -cp target/assignment2-shellsort-1.0.0.jar cli.BenchmarkRunner
```
This automatically:
	1.	Generates test arrays via ArrayGenerator.
	2.	Executes sorting for all (Gap, Pattern, Size) combinations.
	3.	Records metrics into docs/performance-plots/shellsort_results.csv.
	4.	Displays real-time progress in the console for validation.

⸻

3.2 Data Visualization in Google Colab

After generating the CSV, results were visualized in Google Colab using Pandas and Matplotlib.

Example plotting snippet:
```
import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('shellsort_results.csv')

for gap in df['Gap'].unique():
    subset = df[(df['Pattern'] == 'RANDOM') & (df['Gap'] == gap)]
    plt.plot(subset['Size'], subset['Time(ms)'], marker='o', label=gap)

plt.title('Shell Sort Performance — Random Pattern')
plt.xlabel('Input Size (n)')
plt.ylabel('Time (ms)')
plt.legend()
plt.grid(True)
plt.show()
```
Resulting figures were exported to:
```
docs/performance-plots/
```
Generated plots:
	•	time_vs_n_random.png
	•	time_vs_n_sorted.png
	•	time_vs_n_reversed.png
	•	time_vs_n_nearly_sorted.png
	•	time_vs_n_all_patterns.png

⸻

4. Benchmark Environment

| **Component**        | **Specification**         |
|-----------------------|---------------------------|
| Device               | MacBook Air (2020)        |
| Processor            | Apple M1 (ARM 64-bit)     |
| Memory               | 8 GB Unified RAM          |
| Operating System     | macOS Ventura 13.5        |
| JDK Distribution     | Temurin 17                |
| JVM Options          | Default (no special flags) |

**The benchmarks were run on a single-user system with minimal background load.
JVM warm-up runs were skipped for simplicity but are recommended for higher statistical precision**.

⸻

5. CSV Format and Sample Entry
```
Gap,Pattern,Size,Time(ms),Comparisons,Swaps,ArrayAccesses
Sedgewick,RANDOM,10000,12,117234,23456,456789
```
Each line corresponds to a single benchmark case.

_
6. Reproducibility Guidelines

To ensure consistent results:
	
•	Use identical seed and input parameters.
	
•	Maintain the same JDK and OS versions.
	
•	Run on an idle system (avoid background CPU load).
	
•	Perform several warm-up runs to offset JIT compilation bias.

_

7. Notes and Observations
	
•	Benchmarks confirm theoretical expectations: the Sedgewick gap sequence consistently performs best for large n.
	
•	Knuth provides an effective balance between simplicity and runtime.
	
•	Shell’s original sequence is less efficient but remains a valuable baseline for analysis.
	
•	All visualizations were generated via Google Colab using Matplotlib.
