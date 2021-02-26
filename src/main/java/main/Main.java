package main;

import interfaces.*;

public class Main {
    public static void main(String[] args) {
        AbstractUI ui = new CLI();
        ui.run();
    }
}
