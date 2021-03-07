package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import logic.CMDManager;
import logic.Editor;
import org.junit.Test;

public class HelpTest {
    @Test
    public void testRawHelp() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        String result = cmdManager.execute(editor,"help");
        FabricForCommands fabric = new FabricForCommands();
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command: fabric.getAllCommandsArrayList())
            stringBuilder.append(command.getDescription()).append("\n");
        assertEquals(stringBuilder.toString().trim(), result);
    }
}
