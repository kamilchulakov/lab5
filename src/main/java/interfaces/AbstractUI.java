package interfaces;

import logic.CMDManager;
import logic.Editor;

public abstract class AbstractUI implements UI{
    CMDManager cmdManager;
    Editor editor;
    public AbstractUI() {
        cmdManager = new CMDManager();
        editor = new Editor();
        createUI();
    }

    @Override
    public void run() {
        while (true) {
            String input = askForCommand();
            if (isValidCommand(input)) {
                String result = cmdManager.execute(editor, input);
                display(result);
            }
        }
    }

    protected String askForCommand() {
        String input = getCommand();
        if (isValidCommand(input)) {
            if (needsArgs(input)) {
                input += askForArg("arg");
            }
            return input;
        } else {
            display("Try again");
            return "";
        }
    }

    protected String askForArg(String arg) {
        return getArg(arg);
    }

    protected abstract String getArg(String arg);
    protected abstract String getCommand();
    protected abstract void createUI();

    private boolean isValidCommand(String command) {
        return cmdManager.validate(command);
    }
    private boolean needsArgs(String command) {
        return cmdManager.needsArgs(command);
    }
}
