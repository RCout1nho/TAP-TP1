package view;

import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView {
    private final User user;

    private JFrame frame;

    public HomeView(User user){
        this.user = user;
        initialize();
        frame.setVisible(true);
    }

    private void initialize(){
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 262, 302);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 262, 268);
        frame.getContentPane().add(tabbedPane);

        JPanel panelUsers = new JPanel();
        tabbedPane.addTab("Usuários", panelUsers);
        panelUsers.setLayout(null);

        JButton btnNewUser = new JButton("Cadastrar usuário");
        btnNewUser.setBounds(34, 12, 181, 25);
        panelUsers.add(btnNewUser);

        JPanel panelTitles = new JPanel();
        tabbedPane.addTab("Títulos", panelTitles);
        panelTitles.setLayout(null);

        JButton btnNewTitle = new JButton("Cadastrar título");
        btnNewTitle.setBounds(37, 12, 181, 25);
        panelTitles.add(btnNewTitle);

        JPanel panelRents = new JPanel();
        tabbedPane.addTab("Aluguéis", panelRents);
        panelRents.setLayout(null);

        JButton btnNewRent = new JButton("Novo aluguel");
        btnNewRent.setBounds(39, 12, 181, 25);
        panelRents.add(btnNewRent);

        JButton btnDoReturn = new JButton("Fazer devolução");
        btnDoReturn.setBounds(39, 49, 181, 25);
        panelRents.add(btnDoReturn);

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
;