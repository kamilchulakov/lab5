package commands;

import logic.Editor;

public class RemoveLower implements Command{
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "remove_lower <key> - a command which removes the elements\nwhich are lower than <key> one.";
    }

    @Override
    public String exec(Editor editor, String args) {
        if (!editor.getCollection().containsKey(args)) return "Key was not found!";
        editor.removeAllLowerThanByKey(args);
        return "Removed.";
    }
}
