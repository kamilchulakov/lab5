package interfaces;


public class CLI implements Presenter {
    @Override
    public void display(String message) {
        System.out.println(message);
    }
}
