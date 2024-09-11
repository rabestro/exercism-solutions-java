import java.util.Comparator;
import java.util.List;

public final class Poker {

    private final List<Hand> hands;

    public Poker(List<String> hands) {
        this.hands = hands.stream()
                .map(Hand::new)
                .sorted(Comparator.comparing(Hand::getValue))
                .toList();
    }

    public List<String> getBestHands() {
        var bestValue = hands.getFirst().getValue();
        return hands.stream()
                .filter(hand -> hand.getValue().equals(bestValue))
                .map(Hand::toString)
                .toList();
    }
}
