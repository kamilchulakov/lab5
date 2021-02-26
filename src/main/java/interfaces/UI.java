package interfaces;

public interface UI {
    void run();
    void display(String message);
    void askForCommand();
    void askForArg(String arg);
    String getCommand();
    String getArg(String arg);
}
