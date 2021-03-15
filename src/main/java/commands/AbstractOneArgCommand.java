package commands;

public abstract class AbstractOneArgCommand implements Command{
    @Override
    public CommandType[] getCommandType() {
        CommandType[] commandTypes = new CommandType[1];
        commandTypes[0] = CommandType.ONEARG;
        return commandTypes;
    }
}
