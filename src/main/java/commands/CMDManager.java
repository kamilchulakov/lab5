package commands;

import objects.LabWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CMDManager {
    private Queue<Command> commandsQueue = new LinkedList<>();
    private final ArrayList<Command> commands = new ArrayList<>();
    public CMDManager() {
        FabricForCommands fabricForCommands = new FabricForCommands();
        commands.addAll(fabricForCommands.getAllCommandsArrayList());
    }
    public String getHistory(int number) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(commandsQueue.peek()).repeat(Math.max(0, number)));
        return stringBuilder.toString();
    }
    public String execute(HashMap<String, LabWork> hashMap, String commandWithArgs) {
        String justCommand = commandWithArgs;
        //if (justCommand.equals("history")) return getHistory(7);
        Command command = getCommandByString(justCommand);
        commandsQueue.add(command);
        return command.exec(hashMap);
    }
    private Command getCommandByString(String command) {
        for (Command command1: commands) {
            if (command1.getName().equals(command)) {
                return command1;
            }
        }
        return null;
    }

}
