package interfaces;


import java.util.Scanner;

public class CLI extends AbstractPresenter {
    private Scanner scanner;
    public CLI() {
        start();
    }

    protected void start() {
        scanner = new Scanner(System.in);
        printDefaultMessage();
        if (scanner.hasNextLine() & !isListening()) {
            listenCommand();
        } else if (scanner.hasNextLine() & isListening()) {
            listenArgs();
            System.out.println("Something is working wrongly");
        }
    }

    private void listenArgs() {
    }

    private void listenCommand() {
        presenterStatus.setShowedMessage(false);
        presenterStatus.addToInput(scanner.nextLine());
    }

    private void printDefaultMessage() {
        System.out.print("Type a command: ");
    }

    @Override
    public void display(String message) {
        if (!presenterStatus.hasShowedMessage()) {
            presenterStatus.setShowedMessage(true);
            System.out.println(message);
        }
    }

    @Override
    public String getCommandText() {
        return presenterStatus.getCommandText();
    }

    @Override
    public boolean isListening() {
        return presenterStatus.isListening();
    }

    @Override
    public void resetInput() {
        presenterStatus.resetInput();
    }

    @Override
    public boolean needsFullReset() {
        return true;
    }

    @Override
    protected void createUI() {
        scanner = new Scanner(System.in);
    }
}
