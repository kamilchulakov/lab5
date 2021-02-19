package interfaces;

import java.io.InputStream;
import java.io.PrintStream;

public class CLI implements UsableInterface{
    @Override
    public InputStream getInputStream() {
        return System.in;
    }

    @Override
    public PrintStream getOutputStream() {
        return System.out;
    }
}
