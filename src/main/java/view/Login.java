package view;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{

    public Login(){
        super("Your Rental Admin");
        this.setSize(300,250);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFont(new Font("sans-serif", Font.PLAIN, 15));

        // Header
        JLabel lbTitle = new JLabel("Login");
        lbTitle.setBounds(0,20,300,25);
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("sans-serif", Font.BOLD, 20));
        this.add(lbTitle);

        // Login field
        JLabel lbLogin = new JLabel("Username:");
        lbLogin.setBounds(10, 100, 100, 15);
        lbLogin.setFont(new Font("sans-serif", Font.BOLD, 15));
        this.add(lbLogin);

        JTextField tfLogin = new JTextField();
        tfLogin.setBounds(120,100,150,15);
        this.add(tfLogin);

        // Password field
        JLabel lbPassword = new JLabel("Password:");
        lbPassword.setBounds(10, 130, 100, 15);
        lbPassword.setFont(new Font("sans-serif", Font.BOLD, 15));
        this.add(lbPassword);

        JTextField tfPassword = new JTextField();
        tfPassword.setBounds(120,130,150,15);
        this.add(tfPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(170, 160, 100, 20);
        this.add(btnLogin);
    }
}
