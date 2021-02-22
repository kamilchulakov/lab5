package interfaces;


public class CLI implements Presenter {
    @Override
    public void display(String message) {
        System.out.println(message);
    }

    @Override
    public String getCommandText() {
        return null;
    }

    @Override
    public boolean isListening() {
        return false;
    }

    @Override
    public void resetInput() {

    }
}
