class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        for (int i = numberToCheck, pow = digitCount(i); i > 0; i /= 10) {
            int digit = i % 10;
            int power = 1;
            for (int j = 0; j < pow; j++) {
                power *= digit;
            }
            numberToCheck -= power;
        }
        return 0 == numberToCheck;
    }

    private int digitCount(int numberToCheck) {
        int digitCount = 1;
        while (numberToCheck > 9) {
            numberToCheck /= 10;
            digitCount++;
        }
        return digitCount;
    }
}
