package commands;

import logic.Editor;
import logic.InputData;
import objects.LabWork;

import java.util.HashMap;

public class RemoveByKey extends AbstractOneArgCommand{

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public String getDescription() {
        return "remove_key <key> - a command which removes an element by key.";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        if (!editor.getCollection().containsKey(inputData.getCommandArg())) return "Key was not found!";
        editor.removeElementByKey(inputData.getCommandArg());
        return "Successfully removed the element.";
    }
}
