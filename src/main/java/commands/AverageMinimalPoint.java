package commands;

import logic.Editor;
import logic.InputData;

public class AverageMinimalPoint extends AbstractNoInputCommand{
    @Override
    public String getName() {
        return "average_of_minimal_point";
    }

    @Override
    public String getDescription() {
        return "average_of_minimal_point - a command which show an average\n minimal point field value of collection items.";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        return editor.getAverageMinimalPoint();
    }
}
