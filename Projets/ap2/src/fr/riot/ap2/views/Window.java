package fr.riot.ap2.views;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        super("Borne bibliothèque");

        setBounds(0, 0, 900, 600);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void linkView(JPanel panel) {
        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setLayout(null);

        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.validate();
        this.repaint();
    }
}
