package commands;

import logic.Editor;
import logic.InputData;
import objects.LabWork;

import java.util.HashMap;

public interface Command {
    String getName();
    String getDescription();
    String exec(Editor editor, InputData inputData);
    CommandType[] getCommandType();
}
