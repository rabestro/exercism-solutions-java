import java.util.function.UnaryOperator;

public record DescriptionFormatter(int maxWidth) implements UnaryOperator<String> {

    @Override
    public String apply(String s) {
        if (s.length() <= maxWidth) {
            return s;
        }
        return s.substring(0, maxWidth - 3) + "...";
    }
}
