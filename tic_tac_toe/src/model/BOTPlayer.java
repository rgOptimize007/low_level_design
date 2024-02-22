package model;

import enumerations.BOTDifficultyLevel;
import enumerations.PlayerType;

public class BOTPlayer extends Player {

    private BOTDifficultyLevel level;

    public BOTPlayer(String id, String name, char symbol, PlayerType type, BOTDifficultyLevel level) {
        super(id, name, symbol, type);
        this.level = level;
    }

    public BOTDifficultyLevel getLevel() {
        return level;
    }

    public void setLevel(BOTDifficultyLevel level) {
        this.level = level;
    }


}
