package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingGUI extends AbstractPresenter {
    private final JFrame frame;
    private String input;
    private boolean listening;
    private boolean showedMessage;
    public SwingGUI() {
        frame = new JFrame("Lab5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        input = "";
        listening = false;
        showedMessage = false;
        createUI();

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    @Override
    public void display(String message) {
        if (!showedMessage) {
            showedMessage = true;
            JOptionPane.showMessageDialog(frame, message);
        }
    }

    @Override
    public String getCommandText() {
        return input;
    }

    @Override
    public boolean isListening() {
        return listening;
    }

    private void createUI() {
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JButton button = new JButton("Click Me!");
        ActionListener buttonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = askForCommand();
                setShowedMessageFalse();
                if(result != null && result.length() > 0){
                    if (!input.equals("")) input += " ";
                    input += result;
                }
            }
        };
        button.addActionListener(buttonActionListener);

        panel.add(button);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private String askForCommand() {
        return (String) JOptionPane.showInputDialog(
                frame,
                "Type a command",
                "SwingGUI",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "help"
        );
    }

    private void setShowedMessageFalse() {
        showedMessage = false;
    }

    @Override
    public void resetInput() {
        input = "";
    }

    @Override
    public boolean needsFullReset() {
        return false;
    }

}
