package interfaces;

import javax.swing.*;

public class GUI extends AbstractUI{
    JFrame frame;
    @Override
    protected String getArg(String arg) {
        return getInput("Type an "+ arg, arg);
    }

    @Override
    protected String getCommand() {
        return getInput("Type a command", "help");
    }

    @Override
    protected void createUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
    }

    @Override
    public void display(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private String getInput(String heading, String basicValue) {
        return JOptionPane.showInputDialog(heading, basicValue);
    }
}
