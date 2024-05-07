import java.util.List;
import java.util.Set;
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
        return !myCollection.isEmpty() && theirCollection.stream().anyMatch(not(myCollection::contains));
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        return collections.stream()
                .flatMap(Set::stream)
                .distinct()
                .filter(card -> collections.stream().allMatch(set -> set.contains(card)))
                .collect(Collectors.toSet());
    }

    static Set<String> allCards(List<Set<String>> collections) {
        return collections.stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
