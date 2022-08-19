package tp01.view;

import tp01.dto.CreateRentDto;
import tp01.model.Title;
import tp01.model.User;
import tp01.service.RentService;
import tp01.service.TitleService;
import tp01.service.UserService;
import tp01.service.impl.RentMySqlImpl;
import tp01.service.impl.TitleMySqlImp;
import tp01.service.impl.UserMySqlImpl;

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
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

            JComboBox cbClient = new JComboBox(clientUsers.stream().map(u -> u.getName()).toArray());
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

            final List<Integer> days = IntStream.range(1,cbTitle.getSelectedIndex() >=0 ? titles.get(cbTitle.getSelectedIndex()).maxPeriodOfRent+1 : 1).boxed().toList();

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
                    try{
                        Integer clientId = clientUsers.get(cbClient.getSelectedIndex()).getId();
                        Integer employeeId = current_user.getId();
                        Integer titleId = titles.get(cbTitle.getSelectedIndex()).id;
                        Integer days = Integer.parseInt(cbPeriodRent.getSelectedItem() != null ? cbPeriodRent.getSelectedItem().toString() : "0");
                        String startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        String endDate = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        CreateRentDto rent = new CreateRentDto(employeeId, clientId, titleId, startDate, endDate);
                        if(rentService.createRent(rent)){
                            JOptionPane.showMessageDialog(frame, "Aluguel criado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                            frame.setVisible(false);
                        }else{
                            JOptionPane.showMessageDialog(frame, "Ocorreu um erro de comunicação com o DB, tente novamente mais tarde", "Erro interno", JOptionPane.WARNING_MESSAGE);
                        }
                    }catch (Exception err){
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

            cbTitle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Integer> newDays = IntStream.range(1,titles.get(cbTitle.getSelectedIndex()).maxPeriodOfRent+1).boxed().toList();
                    cbPeriodRent.removeAllItems();

                    for (Integer newDay : newDays) {
                        cbPeriodRent.addItem(newDay);
                    }
                }
            });
        }
    }
}
