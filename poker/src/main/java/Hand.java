public class Hand {
    private static final String FOUR_KIND = "(.?)(.)\\2{3}(.?)";
    private static final String FULL_HOUSE = "(.)\\1\\1(.)\\2|(.)\\3(.)\\4\\4";
    private static final String THREE = "(.*)(.)\\2\\2(.*)";
    private static final String TWO_PAIRS = "(.?)(.)\\2(.?)(.)\\4(.?)";
    private static final String ONE_PAIR = "(.*)(.)\\2(.*)";

    private final String representation;
    private final String value;

    public Hand(String representation) {
        this.representation = representation;
        var cards = representation.replaceAll("[1SDHC ]", "")
                .codePoints()
                .map("AKQJ098765432"::indexOf)
                .map("ABCDEFGHIJKLM"::charAt)
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        var isFlush = representation.matches(".0?([SDHC])( .0?\\1){4}");
        var isStright = "ABCDEFGHIJKLM AJKLM".contains(cards);

        if (isStright) {
            value = (isFlush ? "A" : "E") + (cards.startsWith("AJ") ? 'J' : cards.charAt(0));
        } else if (isFlush) {
            value = "D" + cards;
        } else if (cards.matches(FOUR_KIND)) {
            value = cards.replaceFirst(FOUR_KIND, "B$2$1$3");
        } else if (cards.matches(FULL_HOUSE)) {
            value = cards.replaceFirst(FULL_HOUSE, "C$1$4$2$3");
        } else if (cards.matches(THREE)) {
            value = cards.replaceFirst(THREE, "F$2$1$3");
        } else if (cards.matches(TWO_PAIRS)) {
            value = cards.replaceFirst(TWO_PAIRS, "G$2$4$1$3$5");
        } else if (cards.matches(ONE_PAIR)) {
            value = cards.replaceFirst(ONE_PAIR, "H$2$1$3");
        } else {
            value = "I" + cards;
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return representation;
    }
}
