package main;

import interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        UI ui;
        try {
            logger.info(String.format("Found an arg: %s.%n", args[0]));
            ui = new CLI(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.info("Didn't find an arg: infile.");
            ui = new CLI();
        }
        ui.run();
    }
}
