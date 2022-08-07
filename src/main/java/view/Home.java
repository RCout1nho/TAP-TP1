package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.http.HttpHeaders;

public class Home {
    private final JFrame frame;

    public Home(){
        frame = new JFrame("Your Rental Admin");
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JLabel lbTitle = new JLabel("Your Rental Admin");
        lbTitle.setBounds(0,10,500,20);
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("sans-serif", Font.BOLD, 20));
        frame.add(lbTitle);

        JLabel lbSubTitle = new JLabel("Seu sistema de gestão de aluguel de filmes");
        lbSubTitle.setBounds(0,40,500,20);
        lbSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbSubTitle.setFont(new Font("sans-serif", Font.ITALIC, 15));
        frame.add(lbSubTitle);

        // TODO: Como centralizar esse botão?
        JButton btnNewRent = new JButton("Novo aluguel");
        btnNewRent.setBounds(50, 70, 400, 25);
        frame.add(btnNewRent);

        JPanel pButtons = new JPanel();
        pButtons.setLayout(new GridLayout(1,1));
        pButtons.add(new Button("Teste"));
        frame.add(pButtons);

    }

    public JFrame getFrame() {
        return frame;
    }
}
