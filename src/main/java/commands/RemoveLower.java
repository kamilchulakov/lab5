package commands;

import logic.Editor;
import logic.InputData;
import objects.Coordinates;
import objects.Discipline;
import objects.LabWork;

public class RemoveLower extends AbstractElementCommand{
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "remove_lower <key> - a command which removes the elements\nwhich are lower than <key> one.";
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
            editor.removeAllLowerByLabwork(labwork);
        } catch (Exception e) {
            e.printStackTrace();
            return "Some problems with input data.";
        }
        return "Removed.";
    }
}
