package de.skuzzle.jmh;

import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// @State(Scope.Benchmark)
public class EqualsTestTest {

    private boolean doTest(Supplier<EqualsTest> ctor, BiPredicate<EqualsTest, EqualsTest> fun) {
        final EqualsTest e1 = ctor.get();
        final EqualsTest e2 = ctor.get();
        return fun.test(e1, e2);
    }

    @Benchmark
    public void rndTestBaseline(Blackhole bh) {
        bh.consume(EqualsTest.randomInstance());
        bh.consume(EqualsTest.randomInstance());
    }

    @Benchmark
    public void worstCaseTestBaseline(Blackhole bh) {
        bh.consume(EqualsTest.eqInstance());
        bh.consume(EqualsTest.eqInstance());
    }

    @Benchmark
    public void equals1CastClassicWorstCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::eqInstance, EqualsTest::equals1CastClassic);
        bh.consume(result);
    }

    @Benchmark
    public void equals1CastGetClassWorstCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::eqInstance, EqualsTest::equals1CastGetClass);
        bh.consume(result);
    }

    @Benchmark
    public void equals1CastInlineWorstCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::eqInstance, EqualsTest::equals1CastInline);
        bh.consume(result);
    }

    @Benchmark
    public void equals2CastsWorstCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::eqInstance, EqualsTest::equals2Casts);
        bh.consume(result);
    }

    @Benchmark
    public void equals1CastClassicRndCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::randomInstance, EqualsTest::equals1CastClassic);
        bh.consume(result);
    }

    @Benchmark
    public void equals1CastGetClassRndCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::randomInstance, EqualsTest::equals1CastGetClass);
        bh.consume(result);
    }

    @Benchmark
    public void equals1CastInlineRndCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::randomInstance, EqualsTest::equals1CastInline);
        bh.consume(result);
    }

    @Benchmark
    public void equals2CastsRndCase(Blackhole bh) {
        final boolean result = doTest(EqualsTest::randomInstance, EqualsTest::equals2Casts);
        bh.consume(result);
    }
}
