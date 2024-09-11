record Hand(String representation, String value) {
    private static final String FOUR_KIND = "(.?)(.)\\2{3}(.?)";
    private static final String FULL_HOUSE = "(.)\\1\\1(.)\\2|(.)\\3(.)\\4\\4";
    private static final String THREE = "(.*)(.)\\2\\2(.*)";
    private static final String TWO_PAIRS = "(.?)(.)\\2(.?)(.)\\4(.?)";
    private static final String ONE_PAIR = "(.*)(.)\\2(.*)";

    Hand(String representation) {
        this(representation, toValue(representation));
    }

    private static String toValue(String representation) {
        var cards = representation.replaceAll("[1SDHC ]", "")
                .codePoints().map("AKQJ098765432"::indexOf).map("ABCDEFGHIJKLM"::charAt).sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        var isFlush = representation.matches(".0?([SDHC])( .0?\\1){4}");
        var isStright = "ABCDEFGHIJKLM AJKLM".contains(cards);
        return isStright ? (isFlush ? "A" : "E") + (cards.startsWith("AJ") ? 'J' : cards.charAt(0))
                : isFlush ? "D" + cards
                : cards.matches(FOUR_KIND) ? cards.replaceFirst(FOUR_KIND, "B$2$1$3")
                : cards.matches(FULL_HOUSE) ? cards.replaceFirst(FULL_HOUSE, "C$1$4$2$3")
                : cards.matches(THREE) ? cards.replaceFirst(THREE, "F$2$1$3")
                : cards.matches(TWO_PAIRS) ? cards.replaceFirst(TWO_PAIRS, "G$2$4$1$3$5")
                : cards.matches(ONE_PAIR) ? cards.replaceFirst(ONE_PAIR, "H$2$1$3")
                : "I" + cards;
    }

    boolean isSameValue(Hand hand) {
        return value.equals(hand.value);
    }
}
