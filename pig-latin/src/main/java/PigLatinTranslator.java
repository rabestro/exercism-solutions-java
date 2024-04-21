import java.util.regex.Pattern;

public final class PigLatinTranslator {

    private static final Pattern WORD_PATTERN = Pattern.compile("""
            (?<consonants>                  # Define possible consonants
                (?!xr|yt)                   # Consonants should not start with "xr" or "yt"
                y?(qu|[\\w&&[^aeiouy]])*    # The main definition of a consonant group.
            )?                              # The consonants group is optional.
                                            # It is empty if a word starts with a vowel
            (?<base>\\w+)                   # The definition of a base, unmoved groups of letters
            """, Pattern.COMMENTS);
    private static final String PIG_LATIN_FORMAT = "${base}${consonants}ay";

    public String translate(String sentence) {
        return WORD_PATTERN.matcher(sentence).replaceAll(PIG_LATIN_FORMAT);
    }
}
