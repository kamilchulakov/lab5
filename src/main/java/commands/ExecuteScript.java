package commands;

import logic.Editor;

public class ExecuteScript implements Command{
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute_script <filename> - a command which executes a script.";
    }

    @Override
    public String exec(Editor editor, String args) {
        return "";
    }
}
