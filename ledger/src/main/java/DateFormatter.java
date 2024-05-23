import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public record DateFormatter(DateTimeFormatter formatter) implements Function<LocalDate, String> {

    @Override
    public String apply(LocalDate localDate) {
        return localDate.format(formatter);
    }
}
