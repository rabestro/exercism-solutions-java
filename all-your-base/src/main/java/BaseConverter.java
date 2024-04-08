import java.util.ArrayList;

class BaseConverter {
    private long number;

    BaseConverter(int originalBase, int[] originalDigits) {
        validateBase(originalBase);
        for (int digit : originalDigits) {
            validateDigit(digit, originalBase);
            number = number * originalBase + digit;
        }
    }

    int[] convertToBase(int newBase) {
        validateBase(newBase);
        var convertedDigits = new ArrayList<Integer>();
        do {
            convertedDigits.add(0, (int) (number % newBase));
            number /= newBase;
        } while (number > 0);
        return convertedDigits.stream().mapToInt(Integer::intValue).toArray();
    }

    private void validateBase(int base) {
        if (base < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }
    }

    private void validateDigit(int digit, int base) {
        if (digit < 0) {
            throw new IllegalArgumentException("Digits may not be negative.");
        }
        if (digit >= base) {
            throw new IllegalArgumentException("All digits must be strictly less than the base.");
        }
    }
}
