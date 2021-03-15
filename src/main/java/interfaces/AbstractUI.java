package interfaces;

import henchmen.Validator;
import logic.CMDManager;
import logic.Editor;
import logic.InputData;

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
                    String pureCommand = input.split(" ")[0];
                    InputData inputData = getInputData(input, pureCommand);
                    String result = cmdManager.execute(editor, pureCommand, inputData);
                    display(result);
                }
            }
        }
    }

    protected final String askForCommand() {
        String input = getCommand();
        if (isValidCommand(input)) {
            return input;
        } else {
            display("Try again.");
            return "";
        }
    }
    private InputData getInputData(String input, String pureCommand) {
        InputData inputData = new InputData();
        boolean[] flags = validator.getInputDataFlagsForCommand(pureCommand);
        if (flags[0]) {
            if (input.split(" ").length == 1) {
                inputData.setCommandArg(null);
            } else {
                inputData.setCommandArg(input.split(" ")[1]);
            }
        }
        if (flags[1]) {
            while (true) {
                try {
                    inputData.setLabName(askForArg("labwork name"));
                    break;
                } catch (Exception e) {
                    display("Invalid labwork name! Can't be null.");
                }
            }
        }
        if (flags[2]) {
            while (true) {
                try {
                    inputData.setCoordinateX(Integer.parseInt(askForArg("coordinateX")));
                    break;
                } catch (Exception e) {
                    display("Invalid coordinateX! Can't be more than 71.");
                }
            }
        }
        if (flags[3]) {
            while (true) {
                try {
                    inputData.setCoordinateY(Integer.parseInt(askForArg("coordinateY")));
                    break;
                } catch (Exception e) {
                    display("Invalid coordinateY! Can't be more than 556.");
                }
            }
        }
        if (flags[4]) {
            while (true) {
                try {
                    inputData.setMinimalPoint(Long.parseLong(askForArg("minimal point")));
                    break;
                } catch (Exception e) {
                    display("Invalid minimal point! Can't be more less than 1.");
                }
            }
        }
        if (flags[5]) {
            while (true) {
                try {
                    inputData.setDifficulty(askForArg("difficulty"));
                    break;
                } catch (Exception e) {
                    display("Invalid difficulty! Must be easy, impossible or terrible.");
                }
            }
        }
        if (flags[6]) {
            while (true) {
                try {
                    inputData.setDisciplineName(askForArg("discipline name"));
                    break;
                } catch (Exception e) {
                    display("Invalid discipline name! Can't be null.");
                }
            }
        }
        if (flags[7]) {
            while (true) {
                try {
                    inputData.setSelfStudyHours(Long.parseLong(askForArg("study hours")));
                    break;
                } catch (Exception e) {
                    display("Invalid study hours. Can't be null.");
                }
            }
        }
        return inputData;
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

    private boolean isExecuteScript(String input) {
        return validator.isExecuteScript(input);
    }
    private void processCommandFromExecuteScriptLoop(String input) {
        if (isValidCommand(input)) {
            if (isExecuteScript(input)) {
                executeScript(input);
            }
            else {
                String pureCommand = input.split(" ")[0];
                InputData inputData = getInputData(input, pureCommand);
                String result = cmdManager.execute(editor, pureCommand, inputData);
                display(result);
            }
        }
    }
}
