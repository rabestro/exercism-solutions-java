import java.util.Comparator;
import java.util.List;

public final class Poker {

    private final List<Hand> hands;

    public Poker(List<String> hands) {
        this.hands = hands.stream()
                .map(Hand::new)
                .sorted(Comparator.comparing(Hand::value))
                .toList();
    }

    public List<String> getBestHands() {
        return hands.stream()
                .filter(hands.getFirst()::isSameValue)
                .map(Hand::representation)
                .toList();
    }
}
