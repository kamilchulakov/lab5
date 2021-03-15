package commands;

public abstract class AbstractNoInputCommand implements Command{
    @Override
    public final CommandType[] getCommandType() {
        CommandType[] commandTypes = new CommandType[1];
        commandTypes[0] = CommandType.NOINPUT;
        return commandTypes;
    }
}
