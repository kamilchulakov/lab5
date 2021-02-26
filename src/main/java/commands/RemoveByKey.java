package commands;

import logic.Editor;
import objects.LabWork;

import java.util.HashMap;

public class RemoveByKey implements Command{

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public String getDescription() {
        return "remove_key <key> - a command which removes an element by key.";
    }

    @Override
    public String exec(Editor editor, String args) {
        if (!editor.getCollection().containsKey(args)) return "Key was not found!";
        editor.removeElementByKey(args);
        return "Successfully removed the element.";
    }
}
