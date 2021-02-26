package commands;

import logic.Editor;
import objects.LabWork;

import java.util.HashMap;

public class Exit implements Command{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit - a command to exit everything.";
    }

    @Override
    public String exec(Editor editor, String args) {
        System.exit(0);
        return "just exit by clicking!";
    }
}
