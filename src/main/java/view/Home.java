package view;

import model.User;
import service.UserService;
import service.impl.UserMySqlImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class Home{
    public Home(User user){
        JFrame frame = new JFrame("Your Rental Admin");
        frame.add(new MainPanel(user));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(250,250);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel {
        public MainPanel(User user){
            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            this.add(new JLabel("<html><h3><b>Your Rental Admin</b></h3></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel pButtons = new JPanel(new GridBagLayout());
            pButtons.add(new JButton("Novo aluguel"), gbc);
            pButtons.add(new JButton("Adicionar novo título"), gbc);
            pButtons.add(new JButton("Remover título"), gbc);
            pButtons.add(new JButton("Editar/Deletar título"), gbc);
            gbc.weighty = 1;

            this.add(pButtons, gbc);
        }
    }
}
;