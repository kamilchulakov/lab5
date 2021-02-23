package interfaces;

public interface PresenterStatus {
    boolean isListening();
    void resetInput();
    String getCommandText();
}
