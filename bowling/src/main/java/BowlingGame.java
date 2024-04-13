import java.util.stream.IntStream;

class BowlingGame {
    private final BowlingGameLog gameLog = new BowlingGameLog();

    void roll(int pins) {
        ensureGameNotOver();
        gameLog.prepareThrow();
        validatePins(pins);
        gameLog.recordThrow(pins);
    }

    int score() {
        ensureGameOver();
        return totalScore();
    }

    private int totalScore() {
        return IntStream
                .range(0, BowlingGameLog.MAX_FRAMES)
                .map(gameLog::score)
                .sum();
    }

    private void ensureGameOver() {
        if (!gameLog.isGameOver()) {
            throw new IllegalStateException("Score cannot be taken until the end of the game");
        }
    }

    private void ensureGameNotOver() {
        if (gameLog.isGameOver()) {
            throw new IllegalStateException("Cannot roll after game is over");
        }
    }

    private void validatePins(int pins) {
        if (pins < 0) {
            throw new IllegalStateException("Negative roll is invalid");
        }
        if (pins > gameLog.pinsOnLane()) {
            throw new IllegalStateException("Pin count exceeds pins on the lane");
        }
    }
}
