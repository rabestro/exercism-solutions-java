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
        String s;

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
        var thousandSeparator = localeName.equals("en-US") ? "," : ".";

        s = header;

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

                String converted;
                if (transaction.change() < 0) {
                    converted = String.format("%.02f", (transaction.change() / 100) * -1);
                } else {
                    converted = String.format("%.02f", transaction.change() / 100);
                }

                String[] parts = converted.split("\\.");
                String amount = "";
                int count = 1;
                for (int ind = parts[0].length() - 1; ind >= 0; ind--) {
                    if (((count % 3) == 0) && ind > 0) {
                        amount = thousandSeparator + parts[0].charAt(ind) + amount;
                    } else {
                        amount = parts[0].charAt(ind) + amount;
                    }
                    count++;
                }

                if (localeName.equals("nl-NL")) {
                    amount = currencySymbol + " " + amount + decimalSeparator + parts[1];
                } else {
                    amount = currencySymbol + amount + decimalSeparator + parts[1];
                }


                if (transaction.change() < 0 && localeName.equals("en-US")) {
                    amount = "(" + amount + ")";
                } else if (transaction.change() < 0 && localeName.equals("nl-NL")) {
                    amount = currencySymbol + " -" + amount.replace(currencySymbol, "").trim() + " ";
                } else if (localeName.equals("nl-NL")) {
                    amount = " " + amount + " ";
                } else {
                    amount = amount + " ";
                }

                s = s + "\n";
                s = s + String.format("%s | %-25s | %13s",
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
