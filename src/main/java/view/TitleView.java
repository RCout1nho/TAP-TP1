package view;

import dto.CreateTitleDto;
import model.User;
import model.enumerators.TitleTypesEnum;
import service.TitleService;
import service.impl.TitleMySqlImp;
import util.IntegerFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class TitleView {
    TitleService titleService;

    public TitleView(User user){
        titleService = new TitleMySqlImp();
        JFrame frame = new JFrame("Your rental Admin");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(new MainPanel(user, titleService, frame));
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel{
        public MainPanel(User user, TitleService titleService, JFrame frame){
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

            JComboBox cbType = new JComboBox(TitleTypesEnum.values());
            JLabel lbType = new JLabel("Tipo");
            lbType.setBounds(20,100,50,20);
            cbType.setBounds(120,100,200,20);
            this.add(lbType);
            this.add(cbType);

            JLabel lbQuantity = new JLabel("Quantidade");
            JFormattedTextField tfQuantity = new JFormattedTextField(IntegerFormatter.getFormatter());
            lbQuantity.setBounds(20,125,90,20);
            tfQuantity.setBounds(120,125,200,20);
            this.add(lbQuantity);
            this.add(tfQuantity);

            JLabel lbMaxPeriod = new JLabel("Period. Max.");
            JFormattedTextField tfMaxPeriod = new JFormattedTextField(IntegerFormatter.getFormatter());
            lbMaxPeriod.setBounds(20,150,90,20);
            tfMaxPeriod.setBounds(120,150,200,20);
            this.add(lbMaxPeriod);
            this.add(tfMaxPeriod);

            JButton btnCreate = new JButton("Criar");
            btnCreate.setBounds(120, 175, 200,20);
            this.add(btnCreate);

            JButton btnCancel = new JButton("Cancelar");
            btnCancel.setBounds(120, 200, 200,20);
            this.add(btnCancel);


            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CreateTitleDto title = Validators.validate(tfName.getText(), cbType.getSelectedItem().toString(), tfQuantity.getText(), tfMaxPeriod.getText());
                    if(title != null){
                        titleService.createTitle(title);
                        JOptionPane.showMessageDialog(frame, "Título criado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                        frame.setVisible(false);
                    }else {
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

    static class Validators{
        public static CreateTitleDto validate(String name, String type, String quantity, String maxPeriodOfRent){
            try{
                Integer intQuantity = Integer.parseInt(quantity);
                Integer intMaxPeriod = Integer.parseInt(maxPeriodOfRent);
                if(name.isEmpty() || name.isBlank()){
                    throw new Exception();
                }
                List<String> types = Arrays.stream(TitleTypesEnum.values()).map(el -> el.toString()).toList();
               if(!types.contains(type)){
                   throw new Exception();
               }
                return new CreateTitleDto(name, type, intQuantity, intMaxPeriod);
            }catch (Exception e){
                return null;
            }
        }
    }
}
