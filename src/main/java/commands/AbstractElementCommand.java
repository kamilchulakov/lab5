package commands;

public abstract class AbstractElementCommand implements Command{
    @Override
    public CommandType[] getCommandType() {
        CommandType[] commandTypes = new CommandType[1];
        commandTypes[0] = CommandType.ELEMENT;
        return commandTypes;
    }
}
