package view;

import dto.CreateRentDto;
import model.Title;
import model.User;
import service.RentService;
import service.TitleService;
import service.UserService;
import service.impl.RentMySqlImpl;
import service.impl.TitleMySqlImp;
import service.impl.UserMySqlImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RentView {
    RentService rentService;
    UserService userService;
    TitleService titleService;

    public RentView(User user){
        rentService = new RentMySqlImpl();
        userService = new UserMySqlImpl();
        titleService = new TitleMySqlImp();

        JFrame frame = new JFrame("Your rental Admin");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.add(new RentView.MainPanel(user, rentService, userService, titleService, frame));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel{
        public MainPanel(User current_user, RentService rentService, UserService userService , TitleService titleService, JFrame frame){
            ArrayList<User> clientUsers = new ArrayList<>(
                    userService.getClientUsers()
            );

            ArrayList<Title> titles = new ArrayList<>(
                    titleService.getAll()
            );

            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(null);

            JLabel lbPageTitle = new JLabel("Novo Aluguel");
            lbPageTitle.setBounds(0,10,400,40);
            lbPageTitle.setFont(new Font("sans-serif", Font.PLAIN, 20));
            lbPageTitle.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(lbPageTitle);

            JComboBox cbClient = new JComboBox(clientUsers.stream().map(u -> u.name).toArray());
            JLabel lbClient = new JLabel("Cliente");
            lbClient.setBounds(20,75,50,20);
            cbClient.setBounds(120,75,200,20);
            this.add(lbClient);
            this.add(cbClient);

            JComboBox cbTitle = new JComboBox(titles.stream().map(t -> t.name).toArray());
            JLabel lbTitle = new JLabel("Título");
            lbTitle.setBounds(20,100,50,20);
            cbTitle.setBounds(120,100,200,20);
            this.add(lbTitle);
            this.add(cbTitle);

            final List<Integer> days = IntStream.range(1,titles.get(cbTitle.getSelectedIndex()).maxPeriodOfRent).boxed().toList();

            JComboBox cbPeriodRent = new JComboBox(days.toArray());
            JLabel lbPeriodRent = new JLabel("Dias");
            lbPeriodRent.setBounds(20,125,50,20);
            cbPeriodRent.setBounds(120,125,200,20);
            this.add(lbPeriodRent);
            this.add(cbPeriodRent);

            JButton btnCreate = new JButton("Criar");
            btnCreate.setBounds(120, 150, 200,20);
            this.add(btnCreate);

            JButton btnCancel = new JButton("Cancelar");
            btnCancel.setBounds(120, 175, 200,20);
            this.add(btnCancel);

            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer clientId = clientUsers.get(cbClient.getSelectedIndex()).id;
                    Integer employeeId = current_user.id;
                    Integer titleId = titles.get(cbTitle.getSelectedIndex()).id;
                    Integer days = Integer.parseInt(cbPeriodRent.getSelectedItem() != null ? cbPeriodRent.getSelectedItem().toString() : "0");
                    String startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String endDate = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    CreateRentDto rent = new CreateRentDto(employeeId, clientId, titleId, startDate, endDate);
                    rentService.createRent(rent);
                }
            });

            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                }
            });

            cbTitle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Integer> newDays = IntStream.range(1,titles.get(cbTitle.getSelectedIndex()).maxPeriodOfRent).boxed().toList();
                    cbPeriodRent.removeAllItems();

                    for (Integer newDay : newDays) {
                        cbPeriodRent.addItem(newDay);
                    }
                }
            });
        }
    }
}
