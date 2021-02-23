package interfaces;

public interface PresenterStatus {
    boolean isListening();
    boolean hasShowedMessage();
    void setShowedMessage(boolean state);
    void setListening(boolean state);
    void resetInput();
    void addToInput(String message);
    String getCommandText();
}
