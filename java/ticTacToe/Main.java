package ticTacToe;

import ticTacToe.boards.TicTacToeBoard;
import ticTacToe.players.HumanPlayer;
import ticTacToe.players.Player;
import ticTacToe.players.RandomPlayer;
import ticTacToe.players.SequentialPlayer;

import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        int n = 10, m = 10, k = 4;
        List<Player> players = List.of(new HumanPlayer(), new RandomPlayer(), new SequentialPlayer());
        final Game game = new Game(true, players);
        int result = game.play(new TicTacToeBoard(n, m, k, players.size()));
        System.out.println("Game result: " + result);
    }
}