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
    public void display(String status, String message) {
        if (status.equals("Error")) JOptionPane.showMessageDialog(frame,
                message, status, JOptionPane.ERROR_MESSAGE);
        else if (status.equals("Success")) JOptionPane.showMessageDialog(frame,
                message, status, JOptionPane.INFORMATION_MESSAGE);
        else if (status.equals("Failure")) JOptionPane.showMessageDialog(frame,
                message, status, JOptionPane.WARNING_MESSAGE);
        else if (status.equals("Undefined")) JOptionPane.showMessageDialog(frame,
                message, status, JOptionPane.QUESTION_MESSAGE);
        else JOptionPane.showMessageDialog(frame,
                    message, status, JOptionPane.PLAIN_MESSAGE);
    }

    private String getInput(String heading, String basicValue) {
        return JOptionPane.showInputDialog(heading, basicValue);
    }
}
