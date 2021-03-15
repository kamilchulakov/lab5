package logic;

import commands.Command;
import henchmen.FabricForCommands;

import java.util.*;

public class CMDManager {
    private CommandHistory commandHistory = new CommandHistory();
    private final ArrayList<Command> commands = new ArrayList<>();
    public CMDManager() {
        FabricForCommands fabricForCommands = new FabricForCommands();
        commands.addAll(fabricForCommands.getAllCommandsArrayList());
    }
    private String getHistory(int number) {
        return commandHistory.getPureHistory(number);
    }
    public String execute(Editor editor, String justCommand, InputData inputData) {
        //System.out.println(commandWithArgs);
        Command command = getCommandByString(justCommand);
        commandHistory.add(justCommand);
        if (justCommand.equals("history")) return getHistory(7);
        String result;
        try {
            result = command.exec(editor, inputData);
        } catch (NullPointerException e) {
            result = "Command was not found. Try again.";
        }
        return result.trim();
    }
    private Command getCommandByString(String command) {
        for (Command command1: commands) {
            if (command1.getName().equals(command)) {
                return command1;
            }
        }
        return null;
    }

    private String getPureCommandName(String line) {
        return line.toLowerCase().trim().split(" ")[0];
    }

    private String getCommandArg(String line) {
        return line.toLowerCase().trim().split(" ")[1];
    }

    private String getCommandArgs(String line) {
        String[] all = line.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String val: all) stringBuilder.append(val).append(" ");
        return stringBuilder.toString().trim();
    }

    private boolean needsArg(String commandName) {
        return commandName.equals("remove_key") | commandName.equals("remove_lower");
    }

    private boolean needsArgs(String command) {
        return command.equals("remove_any_by_discipline") | command.equals("insert") | command.equals("update") |
                command.equals("remove_lower") | command.equals("replace_if_lower");
    }
}
