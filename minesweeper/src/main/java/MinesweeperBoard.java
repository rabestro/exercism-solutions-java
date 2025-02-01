import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class MinesweeperBoard {
    private final List<String> inputBoard;
    private final int rows;
    private final int cols;

    public MinesweeperBoard(List<String> inputBoard) {
        this.inputBoard = inputBoard;
        rows = inputBoard.size();
        cols = rows > 0 ? inputBoard.getFirst().length() : 0;
    }

    public List<String> withNumbers() {
        return range(0, rows).mapToObj(row -> range(0, cols)
                .map(col -> isMine(row, col) ? '*' : countMines(row, col))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
        ).collect(Collectors.toList());
    }

    private char countMines(int row, int col) {
        int mines = 0;
        for (int rowIndex = Math.max(0, row - 1); rowIndex < Math.min(rows, row + 2); rowIndex++) {
            for (int colIndex = Math.max(0, col - 1); colIndex < Math.min(cols, col + 2); colIndex++) {
                if (isMine(rowIndex, colIndex)) {
                    mines++;
                }
            }
        }
        return mines == 0 ? ' ' : Character.forDigit(mines, 10);
    }

    private boolean isMine(int row, int col) {
        return inputBoard.get(row).charAt(col) == '*';
    }
}
