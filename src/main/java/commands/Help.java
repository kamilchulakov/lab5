package commands;

import logic.Editor;
import objects.LabWork;

import java.util.HashMap;

public class Help implements Command{
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help - a command which gives an information about\n" +
                "every single command which is supported in this app.";
    }

    @Override
    public String exec(Editor editor, String args) {
        FabricForCommands fabric = new FabricForCommands();
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command: fabric.getAllCommandsArrayList())
            stringBuilder.append(command.getDescription()).append("\n");
        return stringBuilder.toString();
    }
}
