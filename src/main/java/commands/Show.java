package commands;

import logic.Editor;

public class Show implements Command{
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show - a command which shows the collection items";
    }

    @Override
    public String exec(Editor editor, String args) {
        return editor.getStringCollection();
    }
}
