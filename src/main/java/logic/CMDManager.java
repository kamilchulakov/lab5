package logic;

import commands.Command;
import commands.FabricForCommands;
import objects.LabWork;

import java.util.*;

public class CMDManager {
    private Queue<Command> commandsQueue = new LinkedList<>();
    private final ArrayList<Command> commands = new ArrayList<>();
    public CMDManager() {
        FabricForCommands fabricForCommands = new FabricForCommands();
        commands.addAll(fabricForCommands.getAllCommandsArrayList());
    }
    private String getHistory(int number) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Command> itr = commandsQueue.iterator();
        int i = 0;
        while (itr.hasNext() & i < number) {
            stringBuilder.append(itr.next().getName()).append("\n");
            System.out.print("");
            i++;
        }
        return stringBuilder.toString();
    }
    public String execute(Editor editor, String commandWithArgs) {
        //System.out.println(commandWithArgs);
        String justCommand = getPureCommandName(commandWithArgs);
        Command command = getCommandByString(justCommand);
        commandsQueue.add(command);
        if (justCommand.equals("history")) return getHistory(7);
        String result;
        try {
            if (needsArg(justCommand))
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

    private boolean needsArg(String commandName) {
        return commandName.equals("remove_key") | commandName.equals("remove_lower");
    }

    public boolean needsArgs(String command) {
        return false;
    }
}
