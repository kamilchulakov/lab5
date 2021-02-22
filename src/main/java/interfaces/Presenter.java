package interfaces;


public interface Presenter {
    void display(String message);
    String getCommandText();
    boolean isListening();
    void resetInput();
}
