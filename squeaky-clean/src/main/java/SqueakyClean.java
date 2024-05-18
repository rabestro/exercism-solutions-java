import java.util.regex.Pattern;

class SqueakyClean {
    private static final Pattern KEBAB_CASE = Pattern.compile("-(\\p{L})");

    static String clean(String identifier) {
        return KEBAB_CASE.matcher(identifier)
                .replaceAll(m -> m.group(1).toUpperCase())
                .replace('0', 'o')
                .replace('7', 't')
                .replace('3', 'e')
                .replace('4', 'a')
                .replace('1', 'l')
                .replace(' ', '_')
                .replaceAll("\\W", "");
    }
}
