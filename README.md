# equals-performance
JMH Benchmark to compare different equals Implementations

This is a benchmarking project that aims to compare different equals implementations and their performance implications

### How to run
First build:
```
mvn clean package
```

Then run the benchmark jar:

```
java -jar .\target\benchmarks.jar
```

Run with parameters to create log files that can be analyzed with JITWatch:

```
-XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading -XX:+LogCompilation
```

### Analysis
TBD
