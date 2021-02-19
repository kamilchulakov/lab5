package main;

import commands.CMDManager;
import interfaces.CLI;
import interfaces.UsableInterface;
import objects.LabWork;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        UsableInterface usableInterface = getCLI();
        InputStream inputStream = usableInterface.getInputStream();
        PrintStream outputStream = usableInterface.getOutputStream();
        CMDManager cmdManager = new CMDManager();

        String filename = args[0];
        System.out.println(filename);
        // HashMap<String, LabWork> hashMap = getStorageFromJsonFile(filename);
        // outputStream.println(cmdManager.execute(hashMap, "history"));
    }

    private static UsableInterface getCLI() {
        return new CLI();
    }

    //private static HashMap<String, LabWork> getStorageFromJsonFile(String filename) {}
}
