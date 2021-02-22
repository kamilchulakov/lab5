package interfaces;

import java.io.InputStream;
import java.io.PrintStream;

public class CLI implements Presenter {
    @Override
    public void display(String message) {
        System.out.println(message);
    }
}
