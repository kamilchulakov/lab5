package commands;

import objects.LabWork;

import java.util.HashMap;

public class Help implements Command{
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help - a command which gives an information about every single command which is supported in this app.";
    }

    @Override
    public String exec(HashMap<String, LabWork> hashMap) {
        return null;
    }
}
