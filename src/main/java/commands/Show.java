package commands;

import logic.Editor;
import logic.InputData;

public class Show extends AbstractNoInputCommand{
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show - a command which shows the collection items";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        return editor.getStringCollection();
    }
}
