import java.util.Arrays;
import java.util.List;

class ResistorColorTrio {
    private static final List<String> COLORS = List.of(
            "black", "brown", "red", "orange", "yellow",
            "green", "blue", "violet", "grey", "white"
    );

    String label(String[] colors) {
        var colorValues = Arrays.stream(colors)
                .mapToInt(COLORS::indexOf)
                .toArray();
        var value = colorValues[0] * 10 + colorValues[1];
        var power = (long) Math.pow(10, colorValues[2]);
        var resistance = value * power;

        String unit = "ohms";
        if (resistance >= 1_000_000_000) {
            unit = "gigaohms";
            resistance /= 1_000_000_000;
        } else if (resistance >= 1_000_000) {
            unit = "megaohms";
            resistance /= 1_000_000;
        } else if (resistance >= 1000) {
            resistance /= 1000;
            unit = "kiloohms";
        }
        return resistance + " " + unit;
    }
}
