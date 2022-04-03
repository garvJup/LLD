package snakeAndLadder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Player {
    private String playerName;
    private int id;

    Player(String name, int id) {
        this.playerName = name;
        this.id = id;
    }
}
