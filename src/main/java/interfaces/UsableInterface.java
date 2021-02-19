package interfaces;

import java.io.InputStream;
import java.io.PrintStream;

public interface UsableInterface {
    InputStream getInputStream();
    PrintStream getOutputStream();
}
