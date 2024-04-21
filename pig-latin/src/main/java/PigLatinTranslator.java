import java.util.regex.Pattern;

public final class PigLatinTranslator {

    private static final Pattern WORD_PATTERN = Pattern.compile("""
            (?<consonants>                  # Capture group for possible consonants at the start of a word
                (?!xr|yt)                   # Look ahead negative. Consonants should not start with "xr" or "yt"
                y?(qu|[\\w&&[^aeiouy]])*    # Allows optional 'y' at start, then 'qu' or any consonant
            )?                              # The entire consonant group is optional (for words starting with vowels)
            (?<base>\\w+)                   # Capture group for the base of the word (remaining letters)
            """, Pattern.COMMENTS);
    private static final String PIG_LATIN_FORMAT = "${base}${consonants}ay";

    public String translate(String sentence) {
        return WORD_PATTERN.matcher(sentence).replaceAll(PIG_LATIN_FORMAT);
    }
}
