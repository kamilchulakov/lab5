package commands;

import logic.Editor;
import objects.Discipline;

public class RemoveAnyByDiscipline implements Command{
    @Override
    public String getName() {
        return "remove_any_by_discipline";
    }

    @Override
    public String getDescription() {
        return "remove_any_by_discipline <discipline> - a command to remove by disc.";
    }

    @Override
    public String exec(Editor editor, String args) {
        String name = args.split(" ")[0];
        Long hours = Long.valueOf(args.split(" ")[1]);
        Discipline discipline = new Discipline(name, hours);
        return editor.removeByDiscipline(discipline);
    }
}
