package interfaces;

import javax.swing.*;

public class SwingGUI implements Presenter{
    public JFrame frame;
    public SwingGUI() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void display(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
