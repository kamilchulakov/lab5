package main;

import interfaces.CLI;
import interfaces.Presenter;
import objects.LabWork;

import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        PresenterFabric fabric = new PresenterFabric(PresenterFabric.Type.CLI);
        Presenter presenter = fabric.getPresenter();
        CMDManager cmdManager = new CMDManager();
        HashMap<String, LabWork> hashMap = new HashMap<>();

        // String filename = args[0];
        // presenter.display(filename);
        // HashMap<String, LabWork> hashMap = getStorageFromJsonFile(filename);
        // outputStream.println(cmdManager.execute(hashMap, "history"));
        while (true) {
            if (!presenter.isListening() & !presenter.getCommandText().equals("")) {
                presenter.display(cmdManager.execute(hashMap, presenter.getCommandText()));
                presenter.resetInput();
                if (presenter.getClass().equals(CLI.class)) presenter = fabric.getPresenter();
            }
        }
    }

    //private static HashMap<String, LabWork> getStorageFromJsonFile(String filename) {}
}
