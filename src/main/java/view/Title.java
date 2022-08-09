package view;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Title {
    public Title(User user){
        JFrame frame = new JFrame("Your rental Admin");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel{
        public MainPanel(User user){
            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(new GridBagLayout());

            JLabel lbTitle = new JLabel("Criar novo título");

            this.add(lbTitle);
        }
    }
}
