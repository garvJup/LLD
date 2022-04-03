package snakeAndLadder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Dice {
    private int numberOfDice;

    Dice(int diceNumber) {
        this.numberOfDice = diceNumber;
    }

    int rollDice() {
        return ((int) (Math.random() * (6 * numberOfDice - 1 * numberOfDice))) + 1;
    }
}
