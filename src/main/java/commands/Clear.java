package commands;

import logic.Editor;

public class Clear implements Command{
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear - a command to clear the collection.";
    }

    @Override
    public String exec(Editor editor, String args) {
        editor.clear();
        return "Successfully cleared the collection.";
    }
}
