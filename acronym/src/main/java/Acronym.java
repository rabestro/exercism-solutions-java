import java.util.function.Supplier;
import java.util.regex.Pattern;

record Acronym(String phrase) implements Supplier<String> {
    private static final Pattern WORDS_PATTERN = Pattern.compile("""
            \\P{Alpha}*                 # Match any number of non-letter characters at the start.
            (?<FirstLetter>\\p{Alpha})  # Find the first letter, capturing it in FirstLetter.
            [\\p{Alpha}']*              # Followed by any number of letters or apostrophes.
            \\P{Alpha}*                 # Ending with any non-letter characters.
            """, Pattern.COMMENTS);

    public String get() {
        return WORDS_PATTERN
                .matcher(phrase)
                .replaceAll("${FirstLetter}")
                .toUpperCase();
    }
}
