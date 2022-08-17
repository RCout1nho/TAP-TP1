package view;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView {
    public HomeView(User user){
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
            JButton btnDoReturn = new JButton("Fazer devolução");
            JButton btnNewTitle = new JButton("Adicionar novo título");
            JButton btnNewUser = new JButton("Novo usuário");

            pButtons.add(btnNewRent, gbc);
            pButtons.add(btnDoReturn, gbc);
            pButtons.add(btnNewTitle, gbc);
            pButtons.add(btnNewUser, gbc);
            gbc.weighty = 1;

            this.add(pButtons, gbc);

            btnNewTitle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TitleView titleView = new TitleView(user);
                }
            });

            btnDoReturn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ReturnView returnView = new ReturnView(user);
                }
            });

            btnNewRent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RentView rentView = new RentView(user);
                }
            });

            btnNewUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UserView userView = new UserView(user);
                }
            });
        }
    }
}
;