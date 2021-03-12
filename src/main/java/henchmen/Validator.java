package henchmen;

import commands.Command;

import java.util.ArrayList;

public class Validator {
    private final ArrayList<Command> commands = new ArrayList<>();

    public Validator() {
        FabricForCommands fabricForCommands = new FabricForCommands();
        commands.addAll(fabricForCommands.getAllCommandsArrayList());
    }

    public boolean validate(String command) {
        if (command == null) System.exit(0);
        String commandName = command.toLowerCase().trim().split(" ")[0];
        Command command1 = getCommandByString(commandName);
        return command1 != null;
    }
    private Command getCommandByString(String command) {
        for (Command command1: commands) {
            if (command1.getName().equals(command)) {
                return command1;
            }
        }
        return null;
    }
    public boolean needsArgs(String command) {
        return command.equals("remove_any_by_discipline");
    }

    public boolean isExecuteScript(String input) {
        return input.split(" ")[0].equals("execute_script");
    }
}
