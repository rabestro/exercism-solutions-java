import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class Ledger {
    private static final Set<String> CURRENCIES = Set.of("USD", "EUR");
    private static final Set<String> LOCALES = Set.of("en-US", "nl-NL");

    public LedgerEntry createLedgerEntry(String date, String description, int change) {
        return new LedgerEntry(LocalDate.parse(date), description, change);
    }

    public String format(String currencyName, String localeName, LedgerEntry[] entries) {
        if (!CURRENCIES.contains(currencyName)) {
            throw new IllegalArgumentException("Invalid currency");
        }
        var currency = Currency.getInstance(currencyName);
        var currencySymbol = currency.getSymbol();

        if (!LOCALES.contains(localeName)) {
            throw new IllegalArgumentException("Invalid locale");
        }
        var locale = Locale.forLanguageTag(localeName);
        var resource = ResourceBundle.getBundle("messages", locale);
        var formatter = DateTimeFormatter.ofPattern(resource.getString("date.pattern"));
        var dateFormatter = new DateFormatter(formatter);
        var descriptionFormatter = new DescriptionFormatter(25);

        var header = "%-10s | %-25s | %-13s".formatted(
                resource.getString("header.date"),
                resource.getString("header.description"),
                resource.getString("header.change")
        );

        var symbols = new DecimalFormatSymbols(locale);
        var decimalSeparator = symbols.getDecimalSeparator();
        var groupingSeparator = resource.getString("decimal-format.grouping-separator").charAt(0);
        symbols.setGroupingSeparator(groupingSeparator);
        symbols.setCurrencySymbol(currencySymbol);
        var decimalFormat = new DecimalFormat(resource.getString("decimal-format.pattern"), symbols);


        var s = header;

        if (entries.length > 0) {
            List<LedgerEntry> neg = new ArrayList<>();
            List<LedgerEntry> pos = new ArrayList<>();
            for (LedgerEntry e : entries) {
                if (e.change() >= 0) {
                    pos.add(e);
                } else {
                    neg.add(e);
                }
            }

            neg.sort(Comparator.comparing(LedgerEntry::date));
            pos.sort(Comparator.comparing(LedgerEntry::date));

            List<LedgerEntry> all = new ArrayList<>();
            all.addAll(neg);
            all.addAll(pos);

            for (LedgerEntry transaction : all) {
                var date = dateFormatter.apply(transaction.date());
                var description = descriptionFormatter.apply(transaction.description());
                var amount = decimalFormat.format(transaction.change() / 100);

                s = s + String.format("\n%s | %-25s | %13s",
                        date,
                        description,
                        amount);
            }

        }

        return s;
    }

    public record LedgerEntry(LocalDate date, String description, double change) {
    }
}
