package view;

import model.User;
import service.UserService;
import service.impl.UserMySqlImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    private final UserService userService;

    private JFrame frame;
    private JTextField tfEmail;
    private JTextField tfPassword;

    public LoginView(){
        userService = new UserMySqlImpl();
        initialize();
        frame.setVisible(true);
    }

    private void initialize(){
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(51, 204, 153));
        panel.setBounds(0, 0, 300, 368);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("My rental admin");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 163, 300, 15);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(300, 0, 290, 368);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setHorizontalTextPosition(SwingConstants.CENTER);
        lblLogin.setBounds(12, 5, 278, 30);
        panel_1.add(lblLogin);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setBounds(12, 164, 70, 15);
        panel_1.add(lbEmail);
        tfEmail = new JTextField();
        tfEmail.setBounds(71, 162, 190, 19);
        panel_1.add(tfEmail);
        tfEmail.setColumns(10);

        JLabel lbPassword = new JLabel("Senha");
        lbPassword.setBounds(12, 193, 70, 15);
        panel_1.add(lbPassword);

        tfPassword = new JPasswordField();
        tfPassword.setColumns(10);
        tfPassword.setBounds(71, 191, 190, 19);
        panel_1.add(tfPassword);

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(144, 235, 117, 25);
        panel_1.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = userService.login(tfEmail.getText(), tfPassword.getText());
                if(user != null){
                    HomeView home = new HomeView(user);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame, "Login/Senha inválidos!", "Erro de validação", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
