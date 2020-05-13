package ticTacToe.boards;

import ticTacToe.Cell;
import ticTacToe.Move;

public interface Position {
    boolean isValid(Move move);

    int getN();

    int getM();

    int getK();

    Cell getCell(int r, int c);
}
