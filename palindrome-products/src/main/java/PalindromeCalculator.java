import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class PalindromeCalculator {

    private static void validateFactors(int minFactor, int maxFactor) {
        if (minFactor > maxFactor) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }
        if (minFactor < 0) {
            throw new IllegalArgumentException("invalid input: factors must be >= 0");
        }
    }

    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        validateFactors(minFactor, maxFactor);

        var palindromeProductsWithFactors = new TreeMap<Long, List<List<Integer>>>();

        for (var i = minFactor; i <= maxFactor; i++) {
            for (var j = i; j <= maxFactor; j++) {
                var product = (long) j * i;
                if (isPalindrome(product)) {
                    palindromeProductsWithFactors
                            .computeIfAbsent(product, $ -> new ArrayList<>())
                            .add(List.of(i, j));
                }
            }
        }
        return palindromeProductsWithFactors;
    }

    private boolean isPalindrome(long number) {
        var numberString = String.valueOf(number);
        var reversedNumberString = new StringBuilder(numberString).reverse().toString();
        return numberString.equals(reversedNumberString);
    }
}
