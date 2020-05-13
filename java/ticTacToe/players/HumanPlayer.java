package ticTacToe.players;

import ticTacToe.Cell;
import ticTacToe.Move;
import ticTacToe.boards.Position;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            Move move;
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            move = new Move(getNumber(), getNumber(), cell);
            if (position.isValid(move)) {
                out.println();
                return move;
            }
            out.println("Move " + move + " is invalid");
            out.println();
        }
    }

    private int getNumber() {
        while (true) {
            if (in.hasNextInt()) {
                return in.nextInt() - 1;
            }
            in.next();
        }
    }
}
