package interfaces;

import henchmen.Validator;
import input_exceptions.CancelException;
import input_exceptions.LessThanException;
import input_exceptions.MoreThanException;
import logic.CMDManager;
import logic.Editor;
import logic.InputData;
import logic.OutputData;
import org.apache.logging.log4j.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbstractUI implements UI{
    Logger logger;
    CMDManager cmdManager;
    Editor editor;
    Validator validator;
    ArrayList<String> cachedFilenames;

    public AbstractUI() {
        cmdManager = new CMDManager();
        editor = new Editor();
        cachedFilenames = new ArrayList<>();
        validator = new Validator();
        logger = LogManager.getLogger(UI.class);
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
                try {
                    InputData inputData = getInputData(input, pureCommand);
                    OutputData result = cmdManager.execute(editor, pureCommand, inputData);
                    logger.info("This is result status: " + result.getStatusMessage());
                    logger.info("This is result:\n" + result.getResultMessage());
                    display(result.getStatusMessage(), result.getResultMessage());
                } catch (CancelException ignored) {}
            }

        }
    }

    protected final String askForCommand() {
        String input = getCommand();
        if (isValidCommand(input)) {
            logger.info("Got a valid command: " + input);
            return input;
        } else {
            logger.warn("Got an invalid command.");
            return "";
        }
    }
    private InputData getInputData(String input, String pureCommand) throws CancelException {
        logger.info("Getting input data.");
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
        if (inputData.equals(new InputData())) logger.info("No input data was provided.");
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
        logger.info("Recognized execute_script.");
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
                try {
                    InputData inputData = getInputData(input, pureCommand);
                    OutputData result = cmdManager.execute(editor, pureCommand, inputData);
                    display(result.getStatusMessage(), result.getResultMessage());
                } catch (CancelException ignored) {};
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
            logger.warn("No arg for command was found.");
            inputData.setCommandArg(null);
        } else {
            inputData.setCommandArg(input.split(" ")[1]);
        }
        logger.info("Got command arg.");
    }
    private void setLabNameToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setLabName(askForArg("labwork name"));
                logger.info("Got labwork name.");
                break;
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            } catch (Exception e) {
                logger.warn("Invalid labwork name.");
                display("Error","Invalid labwork name! Can't be null.");
            }
        }
    }
    private void setCorXToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setCoordinateX(Float.parseFloat(askForArg("coordinateX")));
                logger.info("Got coordinate X.");
                break;
            } catch (MoreThanException e) {
                logger.warn("More than exception for X.");
                display("Error","Invalid coordinateX! Can't be more than " + e.getNumber());
            } catch (LessThanException e) {
                logger.warn("Less than exception for X.");
                display("Error","Invalid coordinateX! Can't be less than " + e.getNumber());
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            } catch (Exception e) {
                logger.error("Unhandled exception for X: " + e.getMessage());
                display("Error","Invalid coordinateX!");
            }
        }
    }
    private void setCorYToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setCoordinateY(Float.parseFloat(askForArg("coordinateY")));
                logger.info("Got coordinate Y.");
                break;
            } catch (MoreThanException e) {
                logger.warn("More than exception for Y.");
                display("Error", "Invalid coordinateY! Can't be more than " + e.getNumber());
            } catch (NumberFormatException e) {
                logger.error("Number format exception for Y.");
                display("Error", "Invalid coordinateY! Check number format.");
            } catch (LessThanException e) {
                logger.warn("Less than exception for Y.");
                display("Error","Invalid coordinateY! Can't be less than " + e.getNumber());
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            } catch (Exception e) {
                logger.error("Unhandled exception: " + e.getMessage());
                display("Error","Invalid coordinateY!");
            }
        }
    }
    private void setMinimalPointToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setMinimalPoint(Long.parseLong(askForArg("minimal point")));
                logger.info("Got minimal point.");
                break;
            } catch (NumberFormatException e) {
                logger.error("Number format exception for minimal point.");
                display("Error", "Invalid minimal! Check number format.");
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            }  catch (Exception e) {
                logger.error("Unhandled exception: " + e.getMessage());
                display("Error","Invalid minimal point! Can't be less than 1.");
            }
        }
    }
    private void setDifficultyToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setDifficulty(askForArg("Difficulty: EASY, IMPOSSIBLE or TERRIBLE"));
                logger.info("Got difficulty.");
                break;
            } catch (IllegalArgumentException e) {
                logger.error("IllegalArgument exception: " + e.getMessage());
                display("Error", "Invalid difficulty! Must be easy, impossible or terrible.");
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            } catch (Exception e) {
                logger.error("Unhandled exception: " + e.getMessage());
                display("Error","Something is wrong.");
            }
        }
    }
    private void setDiscNameToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setDisciplineName(askForArg("discipline name"));
                logger.info("Got discipline name.");
                break;
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            } catch (Exception e) {
                logger.error("Unhandled exception: " + e.getMessage());
                display("Error","Invalid discipline name! Can't be null.");
            }
        }
    }
    private void setDiscHoursToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setSelfStudyHours(Long.parseLong(askForArg("study hours")));
                logger.info("Got discipline hours.");
                break;
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            } catch (Exception e) {
                logger.error("Unhandled exception: " + e.getMessage());
                display("Error","Invalid study hours. Can't be null.");
            }
        }
    }
}
