package logic;

import commands.Command;
import henchmen.CommandHistory;
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
    public OutputData execute(Editor editor, String justCommand, InputData inputData) {
        Command command = getCommandByString(justCommand);
        if (inputData.getCommandArg() != null)
            commandHistory.add(justCommand + " " + inputData.getCommandArg());
        else commandHistory.add(justCommand);
        if (justCommand.equals("history")) return
                new OutputData("Success", getHistory(7));
        OutputData result;
        try {
            result = command.exec(editor, inputData);
        } catch (NullPointerException e) {
            result = new OutputData("Failure", "Command was not found. Try again.");
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
