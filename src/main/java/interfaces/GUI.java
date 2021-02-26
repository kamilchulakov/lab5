package interfaces;

import javax.swing.*;

public class GUI extends AbstractUI{
    JFrame frame;
    @Override
    protected String getArg(String arg) {
        return null;
    }

    @Override
    protected String getCommand() {
        return null;
    }

    @Override
    protected void createUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void display(String message) {

    }
}
