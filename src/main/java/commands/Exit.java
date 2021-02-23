package commands;

import objects.LabWork;

import java.util.HashMap;

public class Exit implements Command{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit - a command to exit everything except this world.";
    }

    @Override
    public String exec(HashMap<String, LabWork> hashMap) {
        System.exit(0);
        return "just exit by clicking!";
    }
}
