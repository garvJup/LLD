package snakeAndLadder;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GameBoard {
    int boardSize;
    private final Dice dice;
    private final Queue<Player> nextTurn;
    private final List<Jumper> snakes;
    private final List<Jumper> ladders;
    private final Map<String, Integer> playersCurrentPosition;

    public GameBoard(Dice dice, Queue<Player> nextTurn, List<Jumper> snakes, List<Jumper> ladders, Map<String, Integer> playersCurrentPosition, int boardSize) {
        this.dice = dice;
        this.nextTurn = nextTurn;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playersCurrentPosition = playersCurrentPosition;
        this.boardSize = boardSize;
    }

    void startGame() {
        while (nextTurn.size() > 1) {
            Player player = nextTurn.poll();
            int currentPosition = playersCurrentPosition.get(player.getPlayerName());
            int diceValue = dice.rollDice();

            int nextCell = diceValue + currentPosition;
            System.out.println("DICE " + diceValue);
            if (nextCell > boardSize) {
                nextTurn.offer(player);
            } else if (nextCell == boardSize) {
                System.out.println("Player " + player.getPlayerName() + " won the game.");
            } else {
                int[] nextPosition = new int[1];
                boolean[] checkLadder = new boolean[1];
                nextPosition[0] = nextCell;
                snakes.forEach(i -> {
                    if (i.getStartPoint() == nextCell) {
                        nextPosition[0] = i.endPoint;
                    }
                });
                if (nextPosition[0] != nextCell) {
                    System.out.println("Player " + player.getPlayerName() + " bitten by snake at position: " + nextCell);
                }
                ladders.forEach(i -> {
                    if (i.getStartPoint() == nextCell) {
                        nextPosition[0] = i.endPoint;
                        checkLadder[0] = true;
                    }
                });
                if (nextPosition[0] != nextCell && checkLadder[0]) {
                    System.out.println("Player " + player.getPlayerName() + "Got ladder present at: " + nextCell);
                }
                if (nextPosition[0] == boardSize) {
                    System.out.println("Player " + player.getPlayerName() + " won the game.");
                } else {
                    playersCurrentPosition.put(player.getPlayerName(), nextPosition[0]);
                    System.out.println(player.getPlayerName() + " is at position " + nextPosition[0]);
                    nextTurn.offer(player);
                }
            }

        }
    }
}
