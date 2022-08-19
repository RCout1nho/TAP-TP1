package tp01.view;

import tp01.dto.CreateUserDto;
import tp01.model.enumerators.UserTypeEnum;
import tp01.service.UserService;
import tp01.service.impl.UserMySqlImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class CreateUserView {
    UserService userService;

    public CreateUserView(tp01.model.User user){
        userService = new UserMySqlImpl();
        JFrame frame = new JFrame("Your rental admin");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(new MainPanel(user, userService, frame));
        frame.setVisible(true);
    }

    static class MainPanel extends JPanel{
        public MainPanel(tp01.model.User user, UserService userService, JFrame frame){
            this.setBorder(new EmptyBorder(10,10,10,10));
            this.setLayout(null);

            JLabel lbTitle = new JLabel("Criar novo usuário");
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

            JTextField tfEmail = new JTextField();
            JLabel lbEmail = new JLabel("E-mail");
            lbEmail.setBounds(20,100,50,20);
            tfEmail.setBounds(120,100,200,20);
            this.add(lbEmail);
            this.add(tfEmail);

            JComboBox cbType = new JComboBox(UserTypeEnum.values());
            JLabel lbType = new JLabel("Tipo");
            lbType.setBounds(20,125,50,20);
            cbType.setBounds(120,125,200,20);
            this.add(lbType);
            this.add(cbType);

            JTextField tfPassword = new JPasswordField();
            JLabel lbPassword = new JLabel("Senha");
            lbPassword.setBounds(20,150,50,20);
            tfPassword.setBounds(120,150,200,20);
            this.add(lbPassword);
            this.add(tfPassword);

            JButton btnCreate = new JButton("Criar");
            btnCreate.setBounds(120, 175, 200,20);
            this.add(btnCreate);

            JButton btnCancel = new JButton("Cancelar");
            btnCancel.setBounds(120, 200, 200,20);
            this.add(btnCancel);

            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CreateUserDto user = Validators.validate(tfName.getText(), tfEmail.getText(), cbType.getSelectedItem().toString(), tfPassword.getText());
                    if(user != null){
                        if(userService.createUser(user)){
                            JOptionPane.showMessageDialog(frame, "Usuário criado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
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

        static class Validators{
            public static CreateUserDto validate(String name, String email, String type, String password){
                try{
                    if(name.isBlank() || name.isEmpty()){
                        throw new Exception();
                    }
                    // TODO: Validar se é e-mail válido
                    if(email.isBlank() || email.isEmpty()){
                        throw new Exception();
                    }
                    List<String> types = Arrays.stream(UserTypeEnum.values()).map(el -> el.toString()).toList();
                    if(!types.contains(type)){
                        throw new Exception();
                    }
                    if(password.isBlank() || password.isEmpty()){
                        throw new Exception();
                    }
                    return new CreateUserDto(name, email, UserTypeEnum.valueOf(type), password);
                }catch (Exception e){
                    return null;
                }
            }
        }
    }
}
