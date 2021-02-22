package main;

import commands.CMDManager;
import interfaces.CLI;
import interfaces.Presenter;
import interfaces.SwingGUI;


public class Main {
    public static void main(String[] args) {
        Presenter presenter = getSwingGUI();
        CMDManager cmdManager = new CMDManager();

        String filename = args[0];
        presenter.display(filename);
        // HashMap<String, LabWork> hashMap = getStorageFromJsonFile(filename);
        // outputStream.println(cmdManager.execute(hashMap, "history"));
    }

    private static Presenter getCLI() {
        return new CLI();
    }

    private static Presenter getSwingGUI() {
        return new SwingGUI();
    }

    //private static HashMap<String, LabWork> getStorageFromJsonFile(String filename) {}
}
