package view;

import model.User;
import service.UserService;
import service.impl.UserMySqlImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    public LoginView(){
        UserService userService = new UserMySqlImpl();

        JFrame frame = new JFrame("Your Rental Admin");
        frame.add(new MainPanel(userService, frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(300,250);
        frame.setResizable(false);
        frame.setFont(new Font("sans-serif", Font.PLAIN, 15));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    static class MainPanel extends JPanel{
        public MainPanel(UserService userService, JFrame frame){
            this.setLayout(null);

            JLabel lbTitle = new JLabel("Login");
            lbTitle.setBounds(0,0,300,40);
            lbTitle.setFont(new Font("sans-serif", Font.BOLD, 20));
            lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(lbTitle);

            JTextField tfEmail = new JTextField();
            JLabel lbEmail = new JLabel("E-mail");
            lbEmail.setBounds(20,75,50,20);
            tfEmail.setBounds(75,75,200,20);
            this.add(lbEmail);
            this.add(tfEmail);

            JTextField tfPassword = new JTextField();
            JLabel lbPassword = new JLabel("Senha");
            lbPassword.setBounds(20,100,50,20);
            tfPassword.setBounds(75,100,200,20);
            this.add(lbPassword);
            this.add(tfPassword);

            JButton btnLogin = new JButton("Entrar");
            btnLogin.setBounds(184, 130, 90,20);
            this.add(btnLogin);

            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    User user = userService.login(tfEmail.getText(), tfPassword.getText());
                    if(user != null){
                        // login
                        HomeView home = new HomeView(user);
                        frame.dispose();
                    }else{
                        // TODO: Mostrar dialog
                        System.out.println("login/Senha inválidos");
                    }
                }
            });
        }
    }
}
