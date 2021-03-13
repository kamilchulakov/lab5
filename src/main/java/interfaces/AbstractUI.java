package interfaces;

import henchmen.Validator;
import logic.CMDManager;
import logic.Editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractUI implements UI{
    CMDManager cmdManager;
    Editor editor;
    Validator validator;
    ArrayList<String> cachedFilenames;

    public AbstractUI() {
        cmdManager = new CMDManager();
        editor = new Editor();
        cachedFilenames = new ArrayList<>();
        validator = new Validator();
        createUI();
    }

    @Override
    public final void run() {
        while (true) {
            String input = askForCommand();
            if (isValidCommand(input)) {
                if (isExecuteScript(input)) {
                    cachedFilenames = new ArrayList<>();
                    executeScript(input);
                }
                else {
                    String result = cmdManager.execute(editor, input);
                    display(result);
                }
            }
        }
    }

    protected final String askForCommand() {
        String input = getCommand();
        if (isValidCommand(input)) {
            input = ifNeedsArgsGetArgs(input);
            return input;
        } else {
            display("Try again");
            return "";
        }
    }

    protected final String askForArg(String arg) {
        return getArg(arg);
    }

    protected abstract String getArg(String arg);
    protected abstract String getCommand();
    protected abstract void createUI();

    private boolean isValidCommand(String command) {
        return validator.validate(command);
    }

    private void executeScript(String input) {
        try {
            String filename = input.split(" ")[1];
            if (!cachedFilenames.contains(filename)) {
                cachedFilenames.add(filename);
                executeScriptLoop(filename);
            } else display("Prevented StackOverflow! Filename: " + filename);
        } catch (ArrayIndexOutOfBoundsException e) {
            display("Invalid filename. Try again!");
        }
    }

    private void executeScriptLoop(String filename) {
        try {
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                processCommandFromExecuteScriptLoop(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            display("Try again! Bad filename: " + filename + ".");
        }
    }

    private boolean needsArgs(String command) {
        return validator.needsArgs(command);
    }
    private boolean isExecuteScript(String input) {
        return validator.isExecuteScript(input);
    }
    private String ifNeedsArgsGetArgs(String input) {
        if (needsArgs(input)) {
            ArrayList<String> args = validator.getArgsForStringCommand(input);
            for (String arg: args) input += " " + askForArg(arg);
        }
        return input;
    }
    private void processCommandFromExecuteScriptLoop(String data) {
        if (isValidCommand(data)) {
            if (isExecuteScript(data)) {
                executeScript(data);
            }
            else {
                data = ifNeedsArgsGetArgs(data);
                String result = cmdManager.execute(editor, data);
                display(result);
            }
        }
    }
}
