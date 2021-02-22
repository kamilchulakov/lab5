package interfaces;

import javax.swing.*;
import java.awt.*;

public class SwingGUI implements Presenter{
    public JFrame frame;
    public SwingGUI() {
        frame = new JFrame();
        //JButton button = new JButton("Click me");
        //button.setBackground(Color.GREEN);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.getContentPane().add(button);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void display(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
