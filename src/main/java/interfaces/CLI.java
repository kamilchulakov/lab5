package interfaces;

import java.util.Scanner;

public class CLI extends AbstractUI{
    public Scanner scanner;

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
        System.out.printf("Status: %s\nMessage:%s\n", status, message);
    }

    private String getInput() {
        return scanner.nextLine();
    }
}
