package interfaces;

import henchmen.Validator;
import logic.CMDManager;
import logic.Editor;

import javax.swing.*;
import java.util.ArrayList;

public class GUI extends AbstractUI{
    JFrame frame;

    public GUI(String filename) {
        cmdManager = new CMDManager();
        editor = new Editor(filename);
        cachedFilenames = new ArrayList<>();
        validator = new Validator();
        createUI();
    }

    public GUI() {
        super();
    }

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
        inputDataFlag = false;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
    }

    @Override
    public void display(String status, String message) {
        int constLength = 150;
        if (message.length() > constLength & !message.contains("help")) {
            int parts = message.length() / constLength;
            StringBuilder message2 = new StringBuilder();
            for (int i=0; i<parts; i++) {
                message2.append(message, i * constLength, (i + 1) * constLength);
                message2.append("\n");
            }
            message2.append(message.substring(parts * constLength));
            message2.append("\n");
            message = message2.toString();
        }
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
