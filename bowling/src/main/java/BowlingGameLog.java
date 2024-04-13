class BowlingGameLog {
    static final int MAX_FRAMES = 10;
    private static final int MAX_PINS = 10;
    private static final int MAX_THROWS_PER_FRAME = 2;
    private final int[] firstThrowNumber = new int[MAX_FRAMES + 2];
    private final int[] knockedDownPins = new int[MAX_FRAMES * MAX_THROWS_PER_FRAME + 1];
    private int frame = -1;
    private int throwNumber = -1;
    private int pinsRemaining;

    int pinsOnLane() {
        return pinsRemaining;
    }

    void prepareThrow() {
        throwNumber++;
        if (isNewFrame()) {
            pinsRemaining = MAX_PINS;
            firstThrowNumber[++frame] = throwNumber;
        }
    }

    void recordThrow(int pins) {
        knockedDownPins[throwNumber] = pins;
        pinsRemaining -= pins;
    }

    public int score(int frame) {
        int i = firstThrowNumber[frame];
        int score = knockedDownPins[i];
        var isStrike = score == MAX_PINS;
        score += knockedDownPins[++i];
        var isSpare = score == MAX_PINS;
        if (isStrike || isSpare) {
            score += knockedDownPins[++i];
        }
        return score;
    }

    private boolean isNewFrame() {
        return frame == -1
               || throwNumber - firstThrowNumber[frame] == MAX_THROWS_PER_FRAME
               || knockedDownPins[firstThrowNumber[frame]] == MAX_PINS;
    }

    boolean isGameOver() {
        return frame == MAX_FRAMES - 1
               && throwNumber - firstThrowNumber[frame] == 1
               && lastTwoThrows() < MAX_PINS
               || frame == MAX_FRAMES
                  && knockedDownPins[throwNumber - 1] < MAX_PINS
               || frame == MAX_FRAMES + 1;
    }

    private int lastTwoThrows() {
        return knockedDownPins[throwNumber - 1] + knockedDownPins[throwNumber];
    }
}
