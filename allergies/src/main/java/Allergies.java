import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Allergies {
    private final List<Allergen> allergies;

    public Allergies(int allergyScore) {
        allergies = Stream
                .of(Allergen.values())
                .filter(a -> (a.getScore() & allergyScore) > 0)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isAllergicTo(Allergen allergen) {
        return allergies.contains(allergen);
    }

    public Collection<Allergen> getList() {
        return allergies;
    }
}