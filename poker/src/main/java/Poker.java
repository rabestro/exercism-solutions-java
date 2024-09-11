import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toUnmodifiableList;

public final class Poker {

    private final List<Hand> hands;

    public Poker(List<String> hands) {
        this.hands = hands.stream()
                .map(Hand::new)
                .sorted((comparing(Hand::getValue)))
                .collect(toUnmodifiableList());
    }

    public List<String> getBestHands() {
        final var bestValue = hands.get(0).getValue();
        return hands.stream()
                .takeWhile(hand -> hand.getValue().equals(bestValue))
                .map(Hand::toString)
                .collect(toUnmodifiableList());
    }
}