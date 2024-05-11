import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public record SudokuSolver(List<Integer> exclude) {
    private static final int MAX_DIGIT = 9;

    public List<List<Integer>> combinations(int sum, int size) {
        return combinations(1, sum, size)
                .map(IntStream::boxed)
                .map(Stream::toList)
                .toList();
    }

    private Stream<IntStream> combinations(int start, int sum, int size) {
        if (size == 1) {
            var solution = start <= sum && sum <= MAX_DIGIT && !exclude.contains(sum);
            return solution ? Stream.of(IntStream.of(sum)) : Stream.empty();
        }
        return IntStream.rangeClosed(start, MAX_DIGIT - size + 1).boxed()
                .filter(not(exclude::contains))
                .flatMap(i -> combinations(i + 1, sum - i, size - 1)
                        .map(s -> IntStream.concat(IntStream.of(i), s)));
    }
}
