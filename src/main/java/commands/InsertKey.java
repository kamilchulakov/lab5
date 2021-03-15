package commands;

import logic.Editor;
import logic.InputData;
import objects.Coordinates;
import objects.Discipline;
import objects.LabWork;

public class InsertKey extends AbstractOneArgElement{
    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getDescription() {
        return "insert <key> - a command which asks to give element info and inserts it by key.";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        try {
            String name = inputData.getDisciplineName();
            Long hours = inputData.getSelfStudyHours();
            Discipline discipline = new Discipline(name, hours);
            Coordinates coordinates = new Coordinates(inputData.getCoordinateX(), inputData.getCoordinateY());
            LabWork labwork = new LabWork(inputData.getLabName(), coordinates, inputData.getMinimalPoint(),
                    inputData.getDifficulty(),discipline);
            editor.insert(inputData.getCommandArg(), labwork);
        } catch (Exception e) {
            e.printStackTrace();
            return "Some problems with input data.";
        }
        return "Inserted.";
    }
}
