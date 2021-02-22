package main;

import interfaces.CLI;
import interfaces.Presenter;
import interfaces.SwingGUI;

public class PresenterFabric {
    Type type;

    PresenterFabric() {
        type = Type.SwingGUI;
    }
    PresenterFabric(Type type1) {
        type = type1;
    }

    public Presenter getPresenter() {
        if (type.equals(Type.CLI)) return getCLI();
        else return getSwingGUI();
    }

    private Presenter getCLI() {
        return new CLI();
    }

    private Presenter getSwingGUI() {
        return new SwingGUI();
    }
    enum Type{
        CLI,
        SwingGUI;
    }
}
