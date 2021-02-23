package interfaces;


import java.util.Scanner;

public class CLI extends AbstractPresenter {
    private String input;
    private boolean listening;
    private boolean showedMessage;

    public CLI() {
        input = "";
        listening = false;
        showedMessage = false;
        listenForAnything();
    }

    private void listenForAnything() {
        Scanner scanner = new Scanner(System.in);
        printDefaultMessage();
        if (scanner.hasNextLine() & !listening) {
            listenCommand(scanner);
        } else if (scanner.hasNextLine() & listening) {
            listenArgs(scanner, input);
            System.out.println("Something is working wrongly");
        }
    }

    private void listenArgs(Scanner scanner, String input) {
    }

    private void listenCommand(Scanner scanner) {
        showedMessage = false;
        input += scanner.nextLine();
    }

    private void printDefaultMessage() {
        System.out.print("Type a command: ");
    }

    @Override
    public void display(String message) {
        if (!showedMessage) {
            showedMessage = true;
            System.out.println(message);
        }
    }

    @Override
    public String getCommandText() {
        return input;
    }

    @Override
    public boolean isListening() {
        return listening;
    }

    @Override
    public void resetInput() {
        input = "";
    }

    @Override
    public boolean needsFullReset() {
        return true;
    }
}
