package view;

import model.User;
import service.UserService;
import service.impl.UserMySqlImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Home{
    public Home(User user){
        JFrame frame = new JFrame("Your Rental Admin");
        frame.add(new MainPanel(user));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(250,250);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel {
        public MainPanel(User user){
            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            this.add(new JLabel("<html><b>O que deseja fazer?</b></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel pButtons = new JPanel(new GridBagLayout());
            JButton btnNewRent = new JButton("Novo aluguel");
            JButton btnNewTitle = new JButton("Adicionar novo título");
            JButton btnNewClient = new JButton("Novo cliente");

            pButtons.add(btnNewRent, gbc);
            pButtons.add(btnNewTitle, gbc);
            pButtons.add(btnNewClient, gbc);
            gbc.weighty = 1;

            this.add(pButtons, gbc);

            btnNewTitle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Title title = new Title(user);
                }
            });

            btnNewRent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    }
}
;