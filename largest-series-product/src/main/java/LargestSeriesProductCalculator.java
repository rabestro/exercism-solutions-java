import java.util.Arrays;
import java.util.function.IntToLongFunction;
import java.util.function.Predicate;

import static java.util.stream.IntStream.rangeClosed;

class LargestSeriesProductCalculator {
    private static final Predicate<String> ALL_DIGITS = s -> s.chars().allMatch(Character::isDigit);
    private final int[] digits;

    LargestSeriesProductCalculator(String input) {
        validateInput(input);
        digits = input.chars().map(Character::getNumericValue).toArray();
    }

    private static void validateInput(String input) {
        if (ALL_DIGITS.negate().test(input)) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
    }

    long calculateLargestProductForSeriesLength(int span) {
        validateSpan(span);
        return rangeClosed(0, digits.length - span)
                .mapToLong(calculateSeriesProduct(span))
                .max()
                .orElse(1);
    }

    private IntToLongFunction calculateSeriesProduct(int span) {
        return start -> Arrays
                .stream(digits, start, start + span)
                .mapToLong(i -> (long) i)
                .reduce((a, b) -> a * b)
                .orElse(1);
    }

    private void validateSpan(int span) {
        if (span < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
        if (span > digits.length) {
            throw new IllegalArgumentException(
                    "Series length must be less than or equal to the length of the string to search.");
        }
    }
}
