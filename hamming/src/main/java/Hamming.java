public record Hamming(String leftStrand, String rightStrand) {

    public Hamming {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
    }

    public int getHammingDistance() {
        return (int) java.util.stream.IntStream
                .range(0, leftStrand.length())
                .filter(i -> leftStrand.charAt(i) != rightStrand.charAt(i))
                .count();
    }
}
