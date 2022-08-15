package view;

import model.User;
import util.IntegerFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Rent {
    public Rent(User user){
        JFrame frame = new JFrame("Your rental Admin");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.add(new Rent.MainPanel());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel{
        public MainPanel(){
            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(null);

            JLabel lbPageTitle = new JLabel("Novo Aluguel");
            lbPageTitle.setBounds(0,10,400,40);
            lbPageTitle.setFont(new Font("sans-serif", Font.PLAIN, 20));
            lbPageTitle.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(lbPageTitle);

            JComboBox cbClient = new JComboBox();
            JLabel lbClient = new JLabel("Cliente");
            lbClient.setBounds(20,75,50,20);
            cbClient.setBounds(120,75,200,20);
            this.add(lbClient);
            this.add(cbClient);

            JComboBox cbTitle = new JComboBox();
            JLabel lbTitle = new JLabel("Título");
            lbTitle.setBounds(20,100,50,20);
            cbTitle.setBounds(120,100,200,20);
            this.add(lbTitle);
            this.add(cbTitle);

            JComboBox cbPeriodRent = new JComboBox();
            JLabel lbPeriodRent = new JLabel("Dias");
            lbPeriodRent.setBounds(20,125,50,20);
            cbPeriodRent.setBounds(120,125,200,20);
            this.add(lbPeriodRent);
            this.add(cbPeriodRent);
        }
    }
}
