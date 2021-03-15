package commands;

import logic.Editor;
import logic.InputData;
import objects.LabWork;

import java.util.HashMap;

public class History extends AbstractNoInputCommand{
    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescription() {
        return "history - a command to show history of commands usage!";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        return "this message shouldn't be here!";
    }
}
