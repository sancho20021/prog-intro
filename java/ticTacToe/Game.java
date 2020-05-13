package ticTacToe;

import ticTacToe.boards.Board;
import ticTacToe.boards.Position;
import ticTacToe.players.Player;

import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final List<Player> players;

    public Game(final boolean log, final List<Player> players) {
        this.log = log;
        this.players = players;
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                final int result = move(board, players.get(i), i);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int playerNo) {
        Position currentPosition = board.getPosition();
        Move move = player.move(currentPosition, board.getCell());
        while (!currentPosition.isValid(move)) {
            move = player.move(currentPosition, board.getCell());
        }
        final Result result = board.makeMove(move);
        log("Player " + (playerNo + 1) + " move: " + move);
        log("Position:\n" + board + '\n');
        if (result == Result.WIN) {
            log("Player " + (playerNo + 1) + " won");
            return playerNo + 1;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
