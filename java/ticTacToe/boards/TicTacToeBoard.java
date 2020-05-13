package ticTacToe.boards;

import ticTacToe.Cell;
import ticTacToe.Move;
import ticTacToe.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board {
    private final int n, m, k, numLength, numberOfPlayers;
    private final Map<Cell, String> SYMBOLS;
    private int emptyCells;

    private final Cell[][] cells;
    private int turn;
    private final Cell[] cellList;
    private final Position position = new Position() {
        @Override
        public boolean isValid(Move move) {
            return isInBorders(move.getRow(), move.getColumn())
                    && cells[move.getRow()][move.getColumn()] == Cell.E
                    && cellList[turn] == move.getValue();
        }

        @Override
        public int getN() {
            return n;
        }

        @Override
        public int getM() {
            return m;
        }

        @Override
        public int getK() {
            return k;
        }

        @Override
        public Cell getCell(int r, int c) {
            return cells[r][c];
        }

        @Override
        public String toString() {
            return TicTacToeBoard.this.toString();
        }
    };

    public TicTacToeBoard(final int n, final int m, final int k, final int numberOfPlayers) {
        cellList = Arrays.copyOfRange(Cell.values(), 1, numberOfPlayers + 1);
        emptyCells = n * m;
        turn = 0;
        cells = new Cell[n][m];
        this.n = n;
        this.m = m;
        this.k = k;
        this.numberOfPlayers = numberOfPlayers;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        SYMBOLS = new HashMap<>();
        numLength = (int) Math.log10(Math.max(n, m)) + 1;
        String space = " ".repeat(numLength);
        for (int i = 0; i < numberOfPlayers; i++) {
            SYMBOLS.put(cellList[i], space + cellList[i].toString());
        }
        SYMBOLS.put(Cell.E, space + ".");
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Cell getCell() {
        return cellList[turn];
    }

    @Override
    public Result makeMove(final Move move) {
        cells[move.getRow()][move.getColumn()] = move.getValue();
        emptyCells--;
        if (isWin(move)) {
            return Result.WIN;
        }
        if (emptyCells == 0) {
            return Result.DRAW;
        }
        turn = (turn + 1) % numberOfPlayers;
        return Result.UNKNOWN;
    }

    private boolean isWin(final Move move) {
        return checkBothDirections(move, 1, 0)
                || checkBothDirections(move, 0, 1)
                || checkBothDirections(move, 1, 1)
                || checkBothDirections(move, 1, -1);
    }

    private boolean checkBothDirections(final Move move, int delR, int delC) {
        int kol = increaseInOneDirection(move.getValue(), move.getRow(), move.getColumn(), delR, delC);
        kol += increaseInOneDirection(move.getValue(), move.getRow(), move.getColumn(), -delR, -delC) - 1;
        return kol >= k;
    }

    private int increaseInOneDirection(Cell value, int r, int c, int delR, int delC) {
        if (!isInBorders(r, c)) {
            return 0;
        }
        int kol = 0;
        int ind = 0;
        while (ind++ < k && isInBorders(r, c)) {
            if (cells[r][c] == value) {
                kol++;
            } else {
                break;
            }
            r += delR;
            c += delC;
        }
        return kol;
    }

    private boolean isInBorders(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    @Override
    public String toString() {
        String space = " ".repeat(numLength + 1);
        StringBuilder sb = new StringBuilder(space);
        String format = "% " + (numLength + 1) + "d";
        for (int i = 1; i <= m; i++) {
            sb.append(String.format(format, i));
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(String.format(format, r + 1));
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
