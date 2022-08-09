package view;

import model.User;
import model.enumerators.ComboBoxItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Title {
    public Title(User user){
        JFrame frame = new JFrame("Your rental Admin");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MainPanel(user));
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel{
        public MainPanel(User user){
            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(null);

            JLabel lbTitle = new JLabel("Criar novo título");
            lbTitle.setBounds(0,10,400,20);
            lbTitle.setFont(new Font("sans-serif", Font.PLAIN, 20));
            lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(lbTitle);

            JTextField tfName = new JTextField();
            JLabel lbName = new JLabel("Nome");
            lbName.setBounds(20,75,50,20);
            tfName.setBounds(120,75,200,20);
            this.add(lbName);
            this.add(tfName);

            Vector<ComboBoxItem> types = new Vector<>();
            types.add(new ComboBoxItem(1, "Bluray"));
            types.add(new ComboBoxItem(2, "DVD"));
            types.add(new ComboBoxItem(3, "CD"));
            types.add(new ComboBoxItem(4, "Fita de vídeo"));
            JComboBox cbType = new JComboBox(types);
            JLabel lbType = new JLabel("Tipo");
            lbType.setBounds(20,100,50,20);
            cbType.setBounds(120,100,200,20);
            this.add(lbType);
            this.add(cbType);

            JTextField tfQuantity = new JTextField();
            JLabel lbQuantity = new JLabel("Quantidade");
            lbQuantity.setBounds(20,125,90,20);
            tfQuantity.setBounds(120,125,200,20);
            this.add(lbQuantity);
            this.add(tfQuantity);

            JButton btnCreate = new JButton("Criar");
            btnCreate.setBounds(120, 150, 200,20);
            this.add(btnCreate);


            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(cbType.getSelectedIndex());
                }
            });
        }
    }
}
