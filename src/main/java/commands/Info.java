package commands;

import logic.Editor;

import java.util.Calendar;

public class Info implements Command{
    private Calendar calendar;
    public Info() {
        calendar = Calendar.getInstance();
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info - a command to get info about the collection:\n type, time, etc";
    }

    @Override
    public String exec(Editor editor, String args) {
            return String.format("Collection info:\nType: %s\nDate: %s\nSize: %s",
                editor.getCollection().getClass().getName(), calendar.getTime().toString(), editor.getCollection().size());
    }
}
