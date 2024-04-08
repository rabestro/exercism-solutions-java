import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * The Acronym class represents an acronym generator that extracts acronyms from a given phrase.
 * It implements the Supplier interface to provide the generated acronym as a result.
 */
record Acronym(String phrase) implements Supplier<String> {
    private static final Pattern ACRONYM_EXTRACTION_PATTERN = Pattern.compile("\\b_?(\\w)[^-\\s]*[- ]*");
    private static final String CAPTURE_GROUP_TEMPLATE = "$1";


    /**
     * Retrieves the acronym generated from the given phrase.
     *
     * @return The acronym extracted from the phrase, represented as uppercase letters.
     */
    public String get() {
        return ACRONYM_EXTRACTION_PATTERN
                .matcher(phrase)
                .replaceAll(CAPTURE_GROUP_TEMPLATE)
                .toUpperCase();
    }
}
