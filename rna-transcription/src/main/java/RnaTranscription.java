class RnaTranscription {

    String transcribe(String dnaStrand) {
        return dnaStrand.chars()
                .map("GCTA"::indexOf)
                .map(i -> new int[]{'C', 'G', 'A', 'U'}[i])
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
