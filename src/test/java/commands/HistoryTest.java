package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import logic.CMDManager;
import logic.Editor;
import org.junit.Test;

public class HistoryTest {
    @Test
    public void testRawHistory() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        String result = cmdManager.execute(editor,"history");
        assertEquals("history\n", result);
    }
    @Test
    public void testTwoHistory() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        cmdManager.execute(editor,"history");
        String result = cmdManager.execute(editor,"history");
        assertEquals("history\nhistory\n", result);
    }
    @Test
    public void testMixedHistory() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        cmdManager.execute(editor,"history");
        cmdManager.execute(editor,"help");
        String result = cmdManager.execute(editor,"history");
        assertEquals("history\nhelp\nhistory\n", result);
    }
}
