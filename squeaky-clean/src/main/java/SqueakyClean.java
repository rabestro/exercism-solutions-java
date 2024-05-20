class SqueakyClean {
    static String clean(String identifier) {
        return identifier
                .codePoints()
                .map(new SqueakyCodePointConverter())
                .filter(i -> i > 0)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
