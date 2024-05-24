import java.text.DecimalFormatSymbols;
import java.util.function.DoubleFunction;

public record ChangeFormatter(DecimalFormatSymbols symbols) implements DoubleFunction<String> {

    @Override
    public String apply(double value) {
//        var decimalFormatSymbols = new DecimalFormatSymbols(locale);
//        var groupingSeparator = resource.getString("decimal-format.grouping-separator").charAt(0);
//        decimalFormatSymbols.setGroupingSeparator(groupingSeparator);
//        var decimalFormat = new DecimalFormat(resource.getString("decimal-format.pattern"), decimalFormatSymbols);

        return "";
    }
}
