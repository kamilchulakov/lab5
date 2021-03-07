package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import logic.CMDManager;
import logic.Editor;
import org.junit.Test;

public class ClearTest {
    @Test
    public void testRawClear() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        cmdManager.execute(editor, "clear");
        assertTrue(editor.getCollection().isEmpty());
    }
}
