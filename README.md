# DAA Assignment 1 - Divide and Conquer & Asymptotic Notations

This project is a Java implementation of Divide-and-Conquer algorithms for the Design and Analysis of Algorithms course.

The assignment focuses on MergeSort, QuickSort and QuickSelect. The algorithms are tested on large integer arrays and their performance is measured using execution time, number of comparisons and maximum recursion depth.

## Algorithms

The project contains three main algorithms:

- MergeSort
- QuickSort
- QuickSelect

### MergeSort

MergeSort divides the array into two smaller parts and recursively sorts them. The two sorted parts are then merged.

The implementation uses one reusable helper array instead of creating a new array during every recursive call.

For small subarrays with 15 or fewer elements, Insertion Sort is used as a cutoff.

The expected time complexity is Θ(n log n).

### QuickSort

QuickSort uses a randomly selected pivot.

The implementation uses three-way partitioning:

- elements smaller than the pivot;
- elements equal to the pivot;
- elements greater than the pivot.

The algorithm recursively processes the smaller partition and uses a while loop for the larger partition. This helps keep the recursion depth small and prevents StackOverflowError on large arrays.

The average expected complexity is Θ(n log n), while the worst case is O(n²).

### QuickSelect

QuickSelect is used to find the k-th smallest element in an array.

The implementation uses the same three-way partitioning method as QuickSort.

After partitioning, only the part containing the required position k is processed. Because only one side is continued, QuickSelect does not need to sort the complete array.

The average complexity is Θ(n), while the worst case is O(n²).

## Metrics

The project contains a Metrics class used to collect algorithm performance information.

The following values are measured:

- execution time;
- number of comparisons;
- maximum recursion depth.

Execution time is measured using System.nanoTime().

A Metrics object is passed to the algorithms instead of using global variables.

## Project Structure

```text
DAA_Assignment1_Karima
│
├── src
│   ├── main
│   │   └── java
│   │       ├── algorithms
│   │       │   ├── MergeSort.java
│   │       │   ├── QuickSort.java
│   │       │   └── QuickSelect.java
│   │       │
│   │       ├── benchmark
│   │       │   └── Benchmark.java
│   │       │
│   │       └── metrics
│   │           └── Metrics.java
│   │
│   └── test
│       └── java
│           └── tests
│               └── AlgorithmTest.java
│
├── plots
│   ├── time_vs_n.png
│   ├── depth_vs_n.png
│   └── ratio_vs_n.png
│
├── create_plots.py
├── results.csv
├── REPORT.md
├── README.md
├── pom.xml
└── .gitignore
```

## Requirements

The project requires:

- Java 17 or newer;
- Maven;
- Python 3 for creating plots;
- pandas;
- matplotlib;
- numpy.

## Build the Project

To build the Maven project, open the terminal in the project directory and run:

```bash
mvn clean package
```

If the build is successful, Maven will compile the project and run the tests.

## Run Tests

JUnit 5 is used for testing.

To run all tests, use:

```bash
mvn test
```

The tests check:

- MergeSort correctness;
- QuickSort correctness;
- QuickSelect correctness;
- empty arrays;
- one-element arrays;
- arrays with equal elements;
- already sorted arrays;
- QuickSort recursion depth.

The sorting results are compared with Java's Arrays.sort().

The QuickSelect result is compared with the corresponding element of a sorted reference array.

## Run the Benchmark

The benchmark is implemented in the Benchmark class.

The following array sizes are tested:

```text
1000
10000
100000
1000000
```

Three input types are used:

```text
random
sorted
duplicates
```

The random input contains random integer values.

The sorted input is already sorted.

The duplicates input contains random values from 0 to 9, which creates many equal elements.

Each algorithm is tested five times for every input type and array size.

The median execution time is used as the final time measurement.

The benchmark measures:

```text
algorithm
input
n
time_ms
comparisons
max_depth
```

The results are saved to:

```text
results.csv
```

## Benchmark Results

The generated CSV file contains the benchmark results for:

- MergeSort;
- QuickSort;
- QuickSelect.

The results can be opened in IntelliJ IDEA, Excel or another spreadsheet program.

## Create Plots

The project uses Python to create the required plots from results.csv.

First, install the required Python libraries:

```bash
pip install pandas matplotlib numpy
```

Then run:

```bash
python create_plots.py
```

The script reads results.csv and creates three PNG files inside the plots directory.

The generated plots are:

```text
time_vs_n.png
depth_vs_n.png
ratio_vs_n.png
```

### Time vs n

The Time vs n plot shows how the execution time changes when the input size increases.

### Recursion Depth vs n

The Recursion Depth vs n plot shows the maximum recursion depth measured for each algorithm.

### Ratio vs n

The Ratio vs n plot is used to check the theoretical Θ bounds.

For MergeSort and QuickSort the ratio is calculated as:

```text
comparisons / (n * log2(n))
```

For QuickSelect the ratio is calculated as:

```text
comparisons / n
```

If the ratio becomes approximately constant as n increases, this supports the expected asymptotic complexity.

## Report

The complete analysis is available in:

```text
REPORT.md
```

The report contains:

- introduction;
- asymptotic bounds;
- best, average and worst cases;
- recurrence relations;
- Master Theorem analysis;
- benchmark setup;
- time results;
- recursion depth results;
- Θ check;
- experimental discussion;
- testing results;
- conclusion.

## Asymptotic Complexity

The main theoretical bounds used in this assignment are:

| Algorithm | Best Case | Average Case | Worst Case |
|---|---|---|---|
| MergeSort | Θ(n log n) | Θ(n log n) | Θ(n log n) |
| QuickSort | Ω(n log n) | Θ(n log n) | O(n²) |
| QuickSelect | Ω(n) | Θ(n) | O(n²) |
| Insertion Sort | Ω(n) | Θ(n²) | O(n²) |

MergeSort has Θ(n log n) complexity in all cases because it always divides the array into approximately equal parts and performs a linear merge.

QuickSort has Θ(n log n) average complexity when the partitions are reasonably balanced. Its worst case is O(n²) when the partitions are very unbalanced.

QuickSelect has Θ(n) average complexity because it continues with only one partition. Its worst case is O(n²) when the pivot repeatedly produces very unbalanced partitions.

Insertion Sort is linear for already sorted input but quadratic for average and worst-case inputs.

## Git Workflow

The project is organized using Git branches.

The feature branches are:

```text
feature/mergesort
feature/quicksort
feature/select
feature/metrics
```

The main branch contains the final working version of the project.

The final release is tagged:

```text
v1.0
```

Example commit messages used for the project include:

```text
feat(mergesort): add insertion sort cutoff
feat(quicksort): add random pivot and three way partition
feat(select): implement quickselect
feat(metrics): add comparison and depth metrics
test(quicksort): check recursion depth
docs(report): add benchmark analysis
```

## Final Deliverables

The final project contains:

1. Java source code for MergeSort, QuickSort and QuickSelect.
2. Metrics class.
3. JUnit 5 tests.
4. Benchmark class.
5. results.csv.
6. Time vs n plot.
7. Recursion Depth vs n plot.
8. Ratio vs n plot.
9. REPORT.md.
10. README.md.
11. Maven project configuration.
12. Git repository with feature branches and v1.0 tag.

## Conclusion

This project demonstrates the implementation and practical analysis of Divide-and-Conquer algorithms.

The algorithms were tested on different input sizes and input types. The benchmark measures execution time, comparisons and recursion depth.

The experimental results can be compared with the theoretical asymptotic complexity discussed in the report.

The project also demonstrates the use of JUnit tests, Maven, Python plotting, CSV results and Git workflow.