import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ledger {
    public LedgerEntry createLedgerEntry(String date, String description, int change) {
        return new LedgerEntry(LocalDate.parse(date), description, change);
    }

    public String format(String cur, String loc, LedgerEntry[] entries) {
        String s;
        String header = null;
        String curSymb = null;
        String datPat = null;
        String decSep = null;
        String thSep = null;
        if (!cur.equals("USD") && !cur.equals("EUR")) {
            throw new IllegalArgumentException("Invalid currency");
        } else if (!loc.equals("en-US") && !loc.equals("nl-NL")) {
            throw new IllegalArgumentException("Invalid locale");
        } else {
            if (cur.equals("USD")) {
                if (loc.equals("en-US")) {
                    curSymb = "$";
                    datPat = "MM/dd/yyyy";
                    decSep = ".";
                    thSep = ",";
                    header = "Date       | Description               | Change       ";
                } else if (loc.equals("nl-NL")) {
                    curSymb = "$";
                    datPat = "dd/MM/yyyy";
                    decSep = ",";
                    thSep = ".";
                    header = "Datum      | Omschrijving              | Verandering  ";
                }
            } else if (cur.equals("EUR")) {
                if (loc.equals("en-US")) {
                    curSymb = "€";
                    datPat = "MM/dd/yyyy";
                    decSep = ".";
                    thSep = ",";
                    header = "Date       | Description               | Change       ";
                } else if (loc.equals("nl-NL")) {
                    curSymb = "€";
                    datPat = "dd/MM/yyyy";
                    decSep = ",";
                    thSep = ".";
                    header = "Datum      | Omschrijving              | Verandering  ";
                }
            }
        }

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

            for (int i = 0; i < all.size(); i++) {
                LedgerEntry e = all.get(i);

                String date = e.date().format(DateTimeFormatter.ofPattern(datPat));

                String desc = e.description();
                if (desc.length() > 25) {
                    desc = desc.substring(0, 22);
                    desc = desc + "...";
                }

                String converted = null;
                if (e.change() < 0) {
                    converted = String.format("%.02f", (e.change() / 100) * -1);
                } else {
                    converted = String.format("%.02f", e.change() / 100);
                }

                String[] parts = converted.split("\\.");
                String amount = "";
                int count = 1;
                for (int ind = parts[0].length() - 1; ind >= 0; ind--) {
                    if (((count % 3) == 0) && ind > 0) {
                        amount = thSep + parts[0].charAt(ind) + amount;
                    } else {
                        amount = parts[0].charAt(ind) + amount;
                    }
                    count++;
                }

                if (loc.equals("nl-NL")) {
                    amount = curSymb + " " + amount + decSep + parts[1];
                } else {
                    amount = curSymb + amount + decSep + parts[1];
                }
                

                if (e.change() < 0 && loc.equals("en-US")) {
                    amount = "(" + amount + ")";
                } else if (e.change() < 0 && loc.equals("nl-NL")) {
                    amount = curSymb + " -" + amount.replace(curSymb, "").trim() + " ";
                } else if (loc.equals("nl-NL")) {
                    amount = " " + amount + " ";
                } else {
                    amount = amount + " ";
                }
                
                s = s + "\n";
                s = s + String.format("%s | %-25s | %13s",
                        date,
                        desc,
                        amount);
            }

        }

        return s;
    }

    public record LedgerEntry(LocalDate date, String description, double change) {
    }

}
