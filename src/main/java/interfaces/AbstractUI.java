package interfaces;

import henchmen.Validator;
import input_exceptions.MoreThanException;
import logic.CMDManager;
import logic.Editor;
import logic.InputData;
import logic.OutputData;

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
            if (isExecuteScript(input)) {
                cachedFilenames = new ArrayList<>();
                executeScript(input);
            }
            else {
                String pureCommand = input.split(" ")[0];
                InputData inputData = getInputData(input, pureCommand);
                OutputData result = cmdManager.execute(editor, pureCommand, inputData);
                display(result.getStatusMessage(), result.getResultMessage());
            }

        }
    }

    protected final String askForCommand() {
        String input = getCommand();
        if (isValidCommand(input)) {
            return input;
        } else {
            return "";
        }
    }
    private InputData getInputData(String input, String pureCommand) {
        InputData inputData = new InputData();
        boolean[] flags = validator.getInputDataFlagsForCommand(pureCommand);
        if (needsArg(flags)) {
            setArgToInputDataLoop(input, inputData);
        }
        if (needsName(flags)) {
            setLabNameToInputDataLoop(inputData);
        }
        if (needsCoordinateX(flags)) {
            setCorXToInputDataLoop(inputData);
        }
        if (needsCoordinateY(flags)) {
            setCorYToInputDataLoop(inputData);
        }
        if (needsMinimalPoint(flags)) {
            setMinimalPointToInputDataLoop(inputData);
        }
        if (needsDifficulty(flags)) {
            setDifficultyToInputDataLoop(inputData);
        }
        if (needsDiscName(flags)) {
            setDiscNameToInputDataLoop(inputData);
        }
        if (needsDiscHours(flags)) {
            setDiscHoursToInputDataLoop(inputData);
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
            } else display("Error","Prevented StackOverflow! Filename: " + filename);
        } catch (ArrayIndexOutOfBoundsException e) {
            display("Error", "Invalid filename. Try again!");
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
            display("Error", "Try again! Bad filename: " + filename + ".");
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
                OutputData result = cmdManager.execute(editor, pureCommand, inputData);
                display(result.getStatusMessage(), result.getResultMessage());
            }
        }
    }
    private boolean needsArg(boolean[] flags) {
        return flags[0];
    }
    private boolean needsName(boolean[] flags) {
        return flags[1];
    }
    private boolean needsCoordinateX(boolean[] flags) {
        return flags[2];
    }
    private boolean needsCoordinateY(boolean[] flags) {
        return flags[3];
    }
    private boolean needsMinimalPoint(boolean[] flags) {
        return flags[4];
    }
    private boolean needsDifficulty(boolean[] flags) {
        return flags[5];
    }
    private boolean needsDiscName(boolean[] flags) {
        return flags[6];
    }
    private boolean needsDiscHours(boolean[] flags) {
        return flags[7];
    }
    private void setArgToInputDataLoop(String input, InputData inputData) {
        if (input.split(" ").length == 1) {
            inputData.setCommandArg(null);
        } else {
            inputData.setCommandArg(input.split(" ")[1]);
        }
    }
    private void setLabNameToInputDataLoop(InputData inputData) {
        while (true) {
            try {
                inputData.setLabName(askForArg("labwork name"));
                break;
            } catch (Exception e) {
                display("Error","Invalid labwork name! Can't be null.");
            }
        }
    }
    private void setCorXToInputDataLoop(InputData inputData) {
        while (true) {
            try {
                inputData.setCoordinateX(Float.parseFloat(askForArg("coordinateX")));
                break;
            } catch (MoreThanException e) {
                display("Error","Invalid coordinateX! Can't be more than " + e.getNumber());
            } catch (Exception e) {
                display("Error","Invalid coordinateX!");
            }
        }
    }
    private void setCorYToInputDataLoop(InputData inputData) {
        while (true) {
            try {
                inputData.setCoordinateY(Float.parseFloat(askForArg("coordinateY")));
                break;
            } catch (MoreThanException e) {
                display("Error", "Invalid coordinateY! Can't be more than " + e.getNumber());
            } catch (NumberFormatException e) {
                display("Error", "Invalid coordinateY! Check number format.");
            } catch (Exception e) {
                display("Error","Invalid coordinateY!");
            }
        }
    }
    private void setMinimalPointToInputDataLoop(InputData inputData) {
        while (true) {
            try {
                inputData.setMinimalPoint(Long.parseLong(askForArg("minimal point")));
                break;
            } catch (Exception e) {
                display("Error","Invalid minimal point! Can't be less than 1.");
            }
        }
    }
    private void setDifficultyToInputDataLoop(InputData inputData) {
        while (true) {
            try {
                inputData.setDifficulty(askForArg("difficulty: EASY, IMPOSSIBLE or TERRIBLE"));
                break;
            } catch (Exception e) {
                display("Error","Invalid difficulty! Must be easy, impossible or terrible.");
            }
        }
    }
    private void setDiscNameToInputDataLoop(InputData inputData) {
        while (true) {
            try {
                inputData.setDisciplineName(askForArg("discipline name"));
                break;
            } catch (Exception e) {
                display("Error","Invalid discipline name! Can't be null.");
            }
        }
    }
    private void setDiscHoursToInputDataLoop(InputData inputData) {
        while (true) {
            try {
                inputData.setSelfStudyHours(Long.parseLong(askForArg("study hours")));
                break;
            } catch (Exception e) {
                display("Error","Invalid study hours. Can't be null.");
            }
        }
    }
}
