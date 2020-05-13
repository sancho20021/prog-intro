package ticTacToe.boards;


import ticTacToe.Cell;
import ticTacToe.Move;
import ticTacToe.Result;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Position getPosition();

    Result makeMove(Move move);

    Cell getCell();
}
