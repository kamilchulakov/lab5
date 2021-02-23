package interfaces;

public class PresenterStatusImpl implements PresenterStatus{
    private boolean listening;
    private String input;
    private boolean showedMessage;

    public PresenterStatusImpl() {
        setListening(false);
        resetInput();
        setShowedMessage(false);
    }

    @Override
    public boolean isListening() {
        return listening;
    }

    @Override
    public boolean hasShowedMessage() {
        return showedMessage;
    }

    @Override
    public void setShowedMessage(boolean state) {
        showedMessage = state;
    }

    @Override
    public void setListening(boolean state) {
        listening = state;
    }

    @Override
    public void resetInput() {
        input = "";
    }

    @Override
    public void addToInput(String message) {
        input += message + " ";
    }

    @Override
    public String getCommandText() {
        input = input.strip();
        input = input.toLowerCase();
        return input;
    }
}
