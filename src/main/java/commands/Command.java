package commands;

import objects.LabWork;

import java.util.HashMap;

public interface Command {
    String getName();
    String getDescription();
    String exec(HashMap<String, LabWork> hashMap);
}
