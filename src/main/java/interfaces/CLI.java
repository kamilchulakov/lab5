package interfaces;


import java.util.Scanner;

public class CLI implements Presenter {
    private String input;
    private boolean listening;
    private boolean showedMessage;
    private boolean printedDefaultMessage;

    public CLI() {
        input = "";
        listening = false;
        showedMessage = false;
        printedDefaultMessage = false;
        listenForAnything();
    }

    private void listenForAnything() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext() & !listening) {
                listenCommand(scanner);
            } else if (scanner.hasNext() & listening) {
                listenArgs(scanner, input);
            } else printDefaultMessage();
            break;
        }
    }

    private void listenArgs(Scanner scanner, String input) {
    }

    private void listenCommand(Scanner scanner) {
        input += scanner.nextLine();
        printedDefaultMessage = false;
    }

    private void printDefaultMessage() {
        if (!printedDefaultMessage) {
            System.out.println("Type a command: ");
            printedDefaultMessage = true;
        }
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
}
