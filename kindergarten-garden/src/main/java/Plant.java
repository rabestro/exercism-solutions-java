enum Plant {
    VIOLETS,
    RADISHES,
    CLOVER,
    GRASS;

    static Plant getPlant(char plantCode) {
        return switch (Character.toChars(plantCode)[0]) {
            case 'G' -> GRASS;
            case 'C' -> CLOVER;
            case 'R' -> RADISHES;
            case 'V' -> VIOLETS;
            default -> null;
        };
    }
}
