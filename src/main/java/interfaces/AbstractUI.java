package interfaces;

import logic.CMDManager;
import logic.Editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
                if (cmdManager.isExecuteScript(input)) {
                    executeScript(input);
                }
                else {
                    String result = cmdManager.execute(editor, input);
                    display(result);
                }
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

    private void executeScript(String input) {
        String filename = input.split(" ")[1];
        try {
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (isValidCommand(data)) {
                    if (needsArgs(data)) {
                        data += askForArg("arg");
                    }
                    if (isValidCommand(data)) {
                        if (cmdManager.isExecuteScript(data)) {
                            executeScript(data);
                        }
                        else {
                            String result = cmdManager.execute(editor, data);
                            display(result);
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            display("Try again! Bad filename: " + filename + ".");
        }
    }

    private boolean needsArgs(String command) {
        return cmdManager.needsArgs(command);
    }
}
