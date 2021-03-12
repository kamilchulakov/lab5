package main;

import interfaces.*;

public class Main {
    public static void main(String[] args) {
        UI ui = new CLI();
        ui.run();
    }
}
