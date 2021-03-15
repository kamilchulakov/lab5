package commands;

import logic.Editor;
import logic.InputData;

public class ExecuteScript extends AbstractOneArgCommand{
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute_script <filename> - a command which executes a script.";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        return "";
    }
}
