package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingGUI extends AbstractPresenter {
    private JFrame frame;
    public SwingGUI() {
        createUI();
        start();
    }

    @Override
    protected void start() {
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    @Override
    protected void createUI() {
        frame = new JFrame("Lab5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                    presenterStatus.addToInput(result);
                }
            }
        };
        button.addActionListener(buttonActionListener);

        panel.add(button);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    @Override
    public void display(String message) {
        if (!presenterStatus.hasShowedMessage()) {
            presenterStatus.setShowedMessage(true);
            JOptionPane.showMessageDialog(frame, message);
        }
    }

    @Override
    public String getCommandText() {
        return presenterStatus.getCommandText();
    }

    @Override
    public boolean isListening() {
        return presenterStatus.isListening();
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
        presenterStatus.setShowedMessage(false);
    }

    @Override
    public void resetInput() {
        presenterStatus.resetInput();
    }

    @Override
    public boolean needsFullReset() {
        return false;
    }

}
