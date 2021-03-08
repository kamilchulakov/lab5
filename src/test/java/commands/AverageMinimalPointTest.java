package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import logic.CMDManager;
import logic.Editor;
import org.junit.Test;

public class AverageMinimalPointTest {
    @Test
    public void testRawAverageMinimalPoint() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        assertEquals(editor.getAverageMinimalPoint(), cmdManager.execute(editor, "average_of_minimal_point"));
    }
    @Test
    public void testClearCollection() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        editor.clear();
        assertEquals(editor.getAverageMinimalPoint(), cmdManager.execute(editor, "average_of_minimal_point"));
    }
}
