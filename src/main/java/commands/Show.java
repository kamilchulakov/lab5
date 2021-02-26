package commands;

import logic.Editor;

public class Show implements Command{
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show is a command which shows the collection items";
    }

    @Override
    public String exec(Editor editor) {
        return editor.getStringCollection();
    }
}
