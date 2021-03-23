package main;

import interfaces.*;

public class Main {
    public static void main(String[] args) {
        UI ui;
        System.out.println("---------------------------------");
        try {
            System.out.printf("Found an arg: %s.%n", args[0]);
            ui = new GUI(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Didn't find an arg: infile.");
            ui = new GUI();
        }
        System.out.println("---------------------------------");
        ui.run();
    }
}
