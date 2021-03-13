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
        return command.equals("remove_any_by_discipline") | command.equals("insert") | command.equals("update") |
                command.equals("remove_lower") | command.equals("replace_if_lower");
    }

    public boolean isExecuteScript(String input) {
        return input.split(" ")[0].equals("execute_script");
    }

    public ArrayList<String> getArgsForStringCommand(String command) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (command.equals("remove_any_by_discipline")) {
            arrayList.add("discipline_name");
            arrayList.add("discipline_hours");
        } else if (command.equals("insert") | command.equals("update") |
        command.equals("remove_lower") | command.equals("replace_if_lower")) {
            arrayList.add("labwork_name");
            arrayList.add("coordinate_x");
            arrayList.add("coordinate_y");
            arrayList.add("minimal_point");
            arrayList.add("difficulty_level");
            arrayList.add("discipline_name");
            arrayList.add("discipline_hours");
        }
        return arrayList;
    }
}
