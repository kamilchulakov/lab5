package commands;

import objects.LabWork;

import java.util.HashMap;

public class History implements Command{
    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescription() {
        return "history is good! learn history!";
    }

    @Override
    public String exec(HashMap<String, LabWork> hashMap) {
        return "nothing is done, don't be angry!";
    }
}
