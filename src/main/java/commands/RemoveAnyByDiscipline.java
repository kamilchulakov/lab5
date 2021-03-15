package commands;

import logic.Editor;
import logic.InputData;
import objects.Discipline;

public class RemoveAnyByDiscipline extends AbstractDisciplineCommand{
    @Override
    public String getName() {
        return "remove_any_by_discipline";
    }

    @Override
    public String getDescription() {
        return "remove_any_by_discipline <discipline> - a command to remove by disc.";
    }

    @Override
    public String exec(Editor editor, InputData inputData) {
        String result;
        try {
            String name = inputData.getDisciplineName();
            Long hours = inputData.getSelfStudyHours();
            Discipline discipline = new Discipline(name, hours);
            result = editor.removeByDiscipline(discipline);
        } catch (NumberFormatException numberFormatException) {
            result = "Invalid number. Try again.";
        }
        return result;
    }
}
