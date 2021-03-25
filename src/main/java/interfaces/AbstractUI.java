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

/**
 * AbstractUI is the base of each UI realisation.
 */
public abstract class AbstractUI implements UI{
    /**
     * Logger is used to make logs.
     */
    Logger logger;
    /**
     * CMDManager is used to make OutputData.
     */
    CMDManager cmdManager;
    /**
     * Editor isn't used. CMDManager gets it.
     */
    Editor editor;
    /**
     * Validator is used in all methods which check something.
     */
    Validator validator;
    /**
     * List of filenames which were used in current execute_script loop.
     * It is used to prevent StackOverFLow.
     */
    ArrayList<String> cachedFilenames;
    /**
     * This flag is used in InputData, to make the difference between GUI and CLI.
     * True = CLI.
     */
    boolean inputDataFlag = true;

    /**
     * Class constructor which isn't modified in children.
     */
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

    /**
     * Provides a command and kinda validates it.
     * @return String command.
     */
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

    /**
     * Provides an InputData
     * @param input is command with arg line.
     * @param pureCommand is a pure command.
     * @return InputData for this command.
     * @throws CancelException if cancel button is pushed.
     */
    private InputData getInputData(String input, String pureCommand) throws CancelException {
        logger.info("Getting input data.");
        InputData inputData = new InputData(inputDataFlag);
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
        if (inputData.equals(new InputData(inputDataFlag))) logger.info("No input data was provided.");
        return inputData;
    }

    /**
     * Provides argument which will be typed.
     * @param arg is a message which will be displayed with input.
     * @return String arg.
     */
    protected final String askForArg(String arg) {
        return getArg(arg);
    }

    /**
     * Provides argument.
     * @param arg is a message which will be displayed with input.
     * @return String arg.
     */
    protected abstract String getArg(String arg);

    /**
     * Provides command and asks it.
     * @return String command.
     */
    protected abstract String getCommand();

    /**
     * It is init method for each UI.
     */
    protected abstract void createUI();

    /**
     * Checks if command is valid,
     * @param command is String command.
     * @return true or false.
     */
    private boolean isValidCommand(String command) {
        return validator.validate(command);
    }

    /**
     * Provides a base for executing a script.
     * @param input is command with arg (filename).
     */
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

    /**
     * Reads the commands and does something. (Like run).
     * @param filename is a name of a file where commands should be listed.
     */
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

    /**
     * Checks if input contains execute_script
     * @param input is command with arg line.
     * @return true or false.
     */
    private boolean isExecuteScript(String input) {
        return validator.isExecuteScript(input);
    }

    /**
     * Continues execute_script loop if a command from the file is valid.
     * @param input is a command with arg.
     */
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

    /**
     * Checks if a command needs arg from its line.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsArg(boolean[] flags) {
        return flags[0];
    }
    /**
     * Checks if a command needs labname.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsName(boolean[] flags) {
        return flags[1];
    }
    /**
     * Checks if a command needs coordinate x.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsCoordinateX(boolean[] flags) {
        return flags[2];
    }
    /**
     * Checks if a command needs coordinate y.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsCoordinateY(boolean[] flags) {
        return flags[3];
    }
    /**
     * Checks if a command needs minimal point.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsMinimalPoint(boolean[] flags) {
        return flags[4];
    }
    /**
     * Checks if a command needs difficulty.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsDifficulty(boolean[] flags) {
        return flags[5];
    }
    /**
     * Checks if a command needs discipline name.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsDiscName(boolean[] flags) {
        return flags[6];
    }
    /**
     * Checks if a command needs study hours.
     * @param flags is flags.
     * @return true or false.
     */
    private boolean needsDiscHours(boolean[] flags) {
        return flags[7];
    }

    /**
     * Sets command arg to InputData
     * @param input is command with arg line.
     * @param inputData is InputData object.
     */
    private void setArgToInputDataLoop(String input, InputData inputData) {
        if (input.split(" ").length == 1) {
            logger.warn("No arg for command was found.");
            inputData.setCommandArg(null);
        } else {
            inputData.setCommandArg(input.split(" ")[1]);
        }
        logger.info("Got command arg.");
    }
    /**
     * Sets labname to InputData
     * @param inputData is InputData object.
     */
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
    /**
     * Sets coordinate x to InputData
     * @param inputData is InputData object.
     */
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
    /**
     * Sets coordinate y to InputData
     * @param inputData is InputData object.
     */
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
    /**
     * Sets minimal point to InputData
     * @param inputData is InputData object.
     */
    private void setMinimalPointToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setMinimalPoint(askForArg("minimal point"));
                logger.info("Got minimal point.");
                break;
            } catch (NullPointerException e) {
                logger.info("Everything is okay, but cancel was pushed.");
                throw new CancelException();
            } catch (NumberFormatException e) {
                logger.error("Number format exception for minimal point.");
                display("Error", "Invalid minimal! Check number format.");
            } catch (LessThanException e) {
                logger.warn("Less than exception for minimal point.");
                display("Error","Invalid minimal point! Can't be less than " + e.getNumber());
            } catch (Exception e) {
                logger.error("Unhandled exception: " + e.getMessage());
                display("Error","Invalid minimal point! Can't be less than 1.");
            }
        }
    }
    /**
     * Sets difficulty to InputData
     * @param inputData is InputData object.
     */
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
    /**
     * Sets discipline name to InputData
     * @param inputData is InputData object.
     */
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
    /**
     * Sets study hours to InputData
     * @param inputData is InputData object.
     */
    private void setDiscHoursToInputDataLoop(InputData inputData) throws CancelException {
        while (true) {
            try {
                inputData.setSelfStudyHours(askForArg("study hours"));
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
