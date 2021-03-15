package commands;

import logic.Editor;
import logic.InputData;
import logic.OutputData;
import objects.LabWork;

import java.util.HashMap;

public class Exit extends AbstractNoInputCommand{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit - a command to exit everything.";
    }

    @Override
    public OutputData exec(Editor editor, InputData inputData) {
        System.exit(0);
        return new OutputData("Exited", "just exit by clicking!");
    }
}
