package main;

import interfaces.CLI;
import interfaces.Presenter;
import interfaces.SwingGUI;
import objects.LabWork;

import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        Presenter presenter = getSwingGUI();
        CMDManager cmdManager = new CMDManager();
        HashMap<String, LabWork> hashMap = new HashMap<>();

        String filename = args[0];
        // presenter.display(filename);
        // HashMap<String, LabWork> hashMap = getStorageFromJsonFile(filename);
        // outputStream.println(cmdManager.execute(hashMap, "history"));
        while (true) {
            if (!presenter.isListening() & !presenter.getCommandText().equals("")) {
                presenter.display(cmdManager.execute(hashMap, presenter.getCommandText()));
                presenter.resetInput();
            } else {
                System.out.print("");
            }
        }
    }

    private static Presenter getCLI() {
        return new CLI();
    }

    private static Presenter getSwingGUI() {
        return new SwingGUI();
    }

    //private static HashMap<String, LabWork> getStorageFromJsonFile(String filename) {}
}
