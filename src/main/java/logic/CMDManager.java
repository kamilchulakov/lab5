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
    public String execute(Editor editor, String commandWithArgs) {
        //System.out.println(commandWithArgs);
        String justCommand = getPureCommandName(commandWithArgs);
        Command command = getCommandByString(justCommand);
        commandHistory.add(commandWithArgs);
        if (justCommand.equals("history")) return getHistory(7);
        String result;
        try {
            if (needsArgs(justCommand))
                result = command.exec(editor, getCommandArgs(commandWithArgs.split(justCommand)[1]));
            else if (needsArg(justCommand))
                result = command.exec(editor, getCommandArg(commandWithArgs));
            else result = command.exec(editor, null);
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

    public boolean validate(String command) {
        if (command == null) System.exit(0);
        String commandName = command.toLowerCase().trim().split(" ")[0];
        Command command1 = getCommandByString(commandName);
        return command1 != null;
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

    public boolean needsArgs(String command) {
        return command.equals("remove_any_by_discipline");
    }

    public boolean isExecuteScript(String input) {
        return input.split(" ")[0].equals("execute_script");
    }
}
