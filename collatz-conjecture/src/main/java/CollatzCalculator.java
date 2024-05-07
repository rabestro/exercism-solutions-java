import static java.util.stream.IntStream.iterate;

class CollatzCalculator {
    long computeStepCount(int start) {
        if (start < 1) throw new IllegalArgumentException("Only positive integers are allowed");
        return iterate(start, i -> i > 1, i -> i % 2 == 0 ? i / 2 : 3 * i + 1).count();
    }
}
