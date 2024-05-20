import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

class DiamondPrinter {

    List<String> printToList(char letter) {
        var diamondSize = letter - 'A';
        return IntStream.rangeClosed(0, diamondSize * 2)
                .mapToObj(new LinePrinter(letter, diamondSize))
                .toList();
    }
}

record LinePrinter(char letter, int diamondSize) implements IntFunction<String> {

    @Override
    public String apply(int lineIndex) {
        if (isEnding(lineIndex)) {
            return endingLine();
        }
        var isTop = lineIndex < diamondSize;
        var padding = isTop ? diamondSize - lineIndex : lineIndex - diamondSize;
        var shift = isTop ? lineIndex : 2 * diamondSize - lineIndex;
        var symbol = (char) ('A' + shift);
        return middleLine(padding, symbol);
    }

    String space(int numberOfSpaces) {
        return " ".repeat(numberOfSpaces);
    }

    String middleLine(int padding, char letter) {
        var innerSpace = 2 * (diamondSize - padding) - 1;
        return space(padding) + letter + space(innerSpace) + letter + space(padding);
    }

    boolean isEnding(int lineIndex) {
        return lineIndex == 0 || lineIndex == 2 * diamondSize;
    }

    String endingLine() {
        return space(diamondSize) + "A" + space(diamondSize);
    }
}
