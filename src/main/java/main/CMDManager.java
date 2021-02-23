package main;

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
    public String getHistory(int number) {
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
    public String execute(HashMap<String, LabWork> hashMap, String commandWithArgs) {
        //System.out.println(commandWithArgs);
        String justCommand = commandWithArgs;
        Command command = getCommandByString(justCommand);
        commandsQueue.add(command);
        if (justCommand.equals("history")) return getHistory(7);
        String result;
        try {
            result = command.exec(hashMap);
        } catch (NullPointerException e) {
            result = "Command was not found. Try again.";
        }
        return result;
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
