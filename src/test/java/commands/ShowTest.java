package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import logic.CMDManager;
import logic.Editor;
import org.junit.Test;

public class ShowTest {
    @Test
    public void testRaw() {
        CMDManager cmdManager = new CMDManager();
        Editor editor = new Editor();
        assertEquals(editor.getStringCollection(), cmdManager.execute(editor, "show"));
    }
}
