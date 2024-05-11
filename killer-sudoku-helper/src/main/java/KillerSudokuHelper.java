import java.util.List;

public class KillerSudokuHelper {
    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        return new SudokuSolver(exclude).combinations(cageSum, cageSize);
    }

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
        return new SudokuSolver(List.of()).combinations(cageSum, cageSize);
    }
}
