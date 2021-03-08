package commands;

import logic.Editor;

public class PrintDescendingDifficulty implements Command{
    @Override
    public String getName() {
        return "print_field_descending_difficulty";
    }

    @Override
    public String getDescription() {
        return "print_field_descending_difficulty - a command which prints descending difficulty.";
    }

    @Override
    public String exec(Editor editor, String args) {
        return editor.getDescendingDifficulty();
    }
}
