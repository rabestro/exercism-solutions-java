import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

final class House {
    private static final String[] PHRASE = {
            "the house that Jack built",
            "the malt that lay in",
            "the rat that ate",
            "the cat that killed",
            "the dog that worried",
            "the cow with the crumpled horn that tossed",
            "the maiden all forlorn that milked",
            "the man all tattered and torn that kissed",
            "the priest all shaven and shorn that married",
            "the rooster that crowed in the morn that woke",
            "the farmer sowing his corn that kept",
            "the horse and the hound and the horn that belonged to"
    };

    public String verse(int verseNumber) {
        return IntStream
                .rangeClosed(1, verseNumber)
                .map(i -> verseNumber - i)
                .mapToObj(i -> PHRASE[i])
                .collect(joining(" ", "This is ", "."));
    }

    public String verses(int startVerse, int endVerse) {
        return IntStream
                .rangeClosed(startVerse, endVerse)
                .mapToObj(this::verse)
                .collect(joining(System.lineSeparator()));
    }

    public String sing() {
        return verses(1, 12);
    }
}