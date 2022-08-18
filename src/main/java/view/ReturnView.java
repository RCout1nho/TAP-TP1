package view;

import dto.RentWithTitleNameDto;
import model.Rent;
import model.Title;
import model.User;
import service.RentService;
import service.UserService;
import service.impl.RentMySqlImpl;
import service.impl.UserMySqlImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ReturnView {
    RentService rentService;
    UserService userService;

    public ReturnView(User user){
        rentService = new RentMySqlImpl();
        userService = new UserMySqlImpl();

        JFrame frame = new JFrame("Your rental Admin");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.add(new MainPanel(user, rentService, userService, frame));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class MainPanel extends JPanel{
        public MainPanel(User user, RentService rentService, UserService userService, JFrame frame){
            ArrayList<User> clientUsers = new ArrayList<>(
                    userService.getClientUsers()
            );

            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(null);

            JLabel lbPageTitle = new JLabel("Fazer devolução");
            lbPageTitle.setBounds(0,10,400,40);
            lbPageTitle.setFont(new Font("sans-serif", Font.PLAIN, 20));
            lbPageTitle.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(lbPageTitle);

            JComboBox cbClient = new JComboBox(clientUsers.stream().map(u -> u.getName()).toArray());
            JLabel lbClient = new JLabel("Cliente");
            lbClient.setBounds(20,75,50,20);
            cbClient.setBounds(120,75,200,20);
            this.add(lbClient);
            this.add(cbClient);

            ArrayList<RentWithTitleNameDto> rentTitles = cbClient.getSelectedIndex() >=0 ?  new ArrayList<>(
                    rentService.getAllByClientId(clientUsers.get(cbClient.getSelectedIndex()).getId())
            ) : new ArrayList<>();

            JComboBox cbTitle = new JComboBox(rentTitles.stream().map(RentWithTitleNameDto::getTitle_name).toArray());
            JLabel lbTitle = new JLabel("Titulo");
            lbTitle.setBounds(20,100,50,20);
            cbTitle.setBounds(120,100,200,20);
            cbTitle.setEnabled(!rentTitles.isEmpty());
            this.add(lbTitle);
            this.add(cbTitle);

            JButton btnCreate = new JButton("Devolver");
            btnCreate.setBounds(120, 125, 200,20);
            this.add(btnCreate);

            JButton btnCancel = new JButton("Cancelar");
            btnCancel.setBounds(120, 150, 200,20);
            this.add(btnCancel);

            cbClient.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<RentWithTitleNameDto> newRents = new ArrayList<>(
                            rentService.getAllByClientId(clientUsers.get(cbClient.getSelectedIndex()).getId())
                    );
                    cbTitle.removeAllItems();
                    rentTitles.clear();
                    rentTitles.addAll(newRents);
                    for(String titleName : rentTitles.stream().map(r -> r.getTitle_name()).toList()){
                        cbTitle.addItem(titleName);
                    }
                    cbTitle.setEnabled(!rentTitles.isEmpty());
                }
            });

            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!rentTitles.isEmpty() && !clientUsers.isEmpty()){
                        RentWithTitleNameDto rent = rentTitles.get(cbTitle.getSelectedIndex());
                        if(rentService.returnARent(rent)){
                            JOptionPane.showMessageDialog(frame, "Devolução feita com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                            frame.setVisible(false);
                        }else{
                            JOptionPane.showMessageDialog(frame, "Ocorreu um erro de comunicação com o DB, tente novamente mais tarde", "Erro interno", JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(frame, "Alguns campos podem estar incorretos", "Erro de validação", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });
        }
    }
}
