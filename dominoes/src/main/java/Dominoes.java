import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

class Dominoes {
    private static final ChainNotFoundException CHAIN_NOT_FOUND_EXCEPTION =
            new ChainNotFoundException("No domino chain found.");

    private static Predicate<Domino> matchesNumber(int number) {
        return domino -> domino.getRight() == number || domino.getLeft() == number;
    }

    List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        if (inputDominoes == null || inputDominoes.isEmpty()) {
            return inputDominoes;
        }
        var dominoes = new LinkedList<>(inputDominoes);
        var result = new LinkedList<Domino>();
        var firstDomino = dominoes.removeFirst();
        result.add(firstDomino);
        var chainNumber = firstDomino.getRight();

        while (!dominoes.isEmpty()) {
            var nextDomino = dominoes.stream()
                    .filter(matchesNumber(chainNumber))
                    .findAny()
                    .orElseThrow(() -> CHAIN_NOT_FOUND_EXCEPTION);
            dominoes.remove(nextDomino);
            if (nextDomino.getRight() == chainNumber) {
                nextDomino = new Domino(nextDomino.getRight(), nextDomino.getLeft());
            }
            result.add(nextDomino);
            chainNumber = nextDomino.getRight();
        }

        if (firstDomino.getLeft() == chainNumber) {
            return result;
        }
        throw CHAIN_NOT_FOUND_EXCEPTION;
    }

}
