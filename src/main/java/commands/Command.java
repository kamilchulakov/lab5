package commands;

import logic.Editor;
import objects.LabWork;

import java.util.HashMap;

public interface Command {
    String getName();
    String getDescription();
    String exec(Editor editor, String args);
}
