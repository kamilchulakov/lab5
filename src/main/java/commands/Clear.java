package commands;

import logic.Editor;
import logic.InputData;

public class Clear extends AbstractNoInputCommand{
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear - a command to clear the collection.";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        editor.clear();
        return "Successfully cleared the collection.";
    }
}
