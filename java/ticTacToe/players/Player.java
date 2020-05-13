package ticTacToe.players;

import ticTacToe.Cell;
import ticTacToe.Move;
import ticTacToe.boards.Position;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Player {
    Move move(Position position, Cell cell);
}
