package interfaces;

import logic.CMDManager;
import logic.Editor;

public abstract class AbstractUI implements UI{
    CMDManager cmdManager;
    Editor editor;
    public AbstractUI() {
        cmdManager = new CMDManager();
        editor = new Editor();
        run();
    }

    @Override
    public void run() {
        while (true) {
            String input = getCommand();
            cmdManager.execute(editor, input);
        }
    }
}
