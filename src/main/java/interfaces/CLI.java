package interfaces;

import henchmen.Validator;
import logic.CMDManager;
import logic.Editor;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI extends AbstractUI{
    public Scanner scanner;

    public CLI(String filename) {
        cmdManager = new CMDManager();
        editor = new Editor(filename);
        cachedFilenames = new ArrayList<>();
        validator = new Validator();
        createUI();
    }

    public CLI() {
        super();
    }

    @Override
    protected String getArg(String arg) {
        System.out.printf("Type an %s: ", arg);
        return getInput();
    }

    @Override
    protected String getCommand() {
        System.out.print("Type a command: ");
        return getInput();
    }

    @Override
    protected void createUI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void display(String status, String message) {
        System.out.printf("-------------------\nStatus: %s\nMessage: %s\n------------------\n", status, message);
    }

    private String getInput() {
        return scanner.nextLine();
    }
}
