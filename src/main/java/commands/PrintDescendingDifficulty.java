package commands;

import logic.Editor;
import logic.InputData;

public class PrintDescendingDifficulty extends AbstractNoInputCommand{
    @Override
    public String getName() {
        return "print_field_descending_difficulty";
    }

    @Override
    public String getDescription() {
        return "print_field_descending_difficulty - a command which prints descending difficulty.";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        return editor.getDescendingDifficulty();
    }
}
