import java.text.MessageFormat;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

class BottleSong {
    private final IntFunction<String> firstHalf = bottles -> MessageFormat.format("""
            {0,choice,1#One|2#Two|3#Three|4#Four|5#Five|6#Six|7#Seven|8#Eight|9#Nine|10#Ten} \
            green bottle{0,choice,1#|1<s} hanging on the wall,
            """, bottles)
            .repeat(2);

    private final IntFunction<String> lastHalf = bottles -> MessageFormat.format("""
            And if one green bottle should accidentally fall,
            There''ll be {0,choice,0#no|1#one|2#two|3#three|4#four|5#five|6#six|7#seven|8#eight|9#nine} \
            green bottle{0,choice,0#s|1#|1<s} hanging on the wall.
            """, bottles);

    private final IntFunction<String> verse = bottles ->
            firstHalf.apply(bottles) + lastHalf.apply(bottles - 1);

    String recite(int startBottles, int takeDown) {
        return IntStream.iterate(startBottles, bottles -> bottles - 1)
                .limit(takeDown)
                .mapToObj(verse)
                .collect(joining(System.lineSeparator()));
    }
}
