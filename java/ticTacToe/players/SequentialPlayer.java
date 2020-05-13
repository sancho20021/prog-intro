package ticTacToe.players;

import ticTacToe.Cell;
import ticTacToe.Move;
import ticTacToe.boards.Position;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {

    @Override
    public Move move(final Position position, final Cell cell) {
        //Board board = (Board) position;
        //board.makeMove(new Move(2, 2, cell));
        int n = position.getN();
        int m = position.getM();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
