package commands;

import logic.Editor;
import objects.LabWork;

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
        try {
            editor.removeAllLowerByLabwork(editor.getElementFromString(args));
        } catch (Exception e) {
            e.printStackTrace();
            return "Some problems with input data.";
        }
        return "Removed.";
    }
}
