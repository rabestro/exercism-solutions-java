import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

class Anagram {
    private final int[] sortedChars;
    private final String sourceWord;

    Anagram(final String word) {
        sourceWord = word;
        sortedChars = toSortedChars(sourceWord);
    }

    List<String> match(final List<String> possibleAnagrams) {
        return possibleAnagrams.stream()
                .filter(this::isAnagram)
                .collect(toUnmodifiableList());
    }

    private boolean isAnagram(final String otherWord) {
        return !sourceWord.equalsIgnoreCase(otherWord)
                && Arrays.equals(sortedChars, toSortedChars(otherWord));
    }

    private int[] toSortedChars(final String word) {
        return word.chars().map(Character::toLowerCase).sorted().toArray();
    }
}