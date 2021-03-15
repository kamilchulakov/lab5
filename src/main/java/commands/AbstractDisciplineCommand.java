package commands;

public abstract class AbstractDisciplineCommand implements Command{
    @Override
    public CommandType[] getCommandType() {
        CommandType[] commandTypes = new CommandType[1];
        commandTypes[0] = CommandType.DISCIPLINE;
        return commandTypes;
    }
}
