import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return Set.copyOf(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        return myCollection.stream().anyMatch(not(theirCollection::contains))
               && theirCollection.stream().anyMatch(not(myCollection::contains));
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        if (collections.isEmpty()) {
            return Set.of();
        }
        var accumulator = new HashSet<>(collections.get(0));
        return collections.stream().reduce(accumulator, commonElements());
    }

    private static BinaryOperator<Set<String>> commonElements() {
        return (acc, elem) -> {
            acc.retainAll(elem);
            return acc;
        };
    }

    static Set<String> allCards(List<Set<String>> collections) {
        return collections.stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
