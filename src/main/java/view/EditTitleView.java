package view;

import model.Title;
import model.enumerators.TitleTypesEnum;
import service.TitleService;
import service.impl.TitleMySqlImp;
import util.IntegerFormatter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditTitleView {
    private JFrame frame;
    private final TitleService titleService;

    public EditTitleView(){
        titleService = new TitleMySqlImp();
        initialize();
        frame.setVisible(true);
    }

    private void initialize(){
        ArrayList<Title> titles = new ArrayList<>(
                titleService.getAll()
        );

        frame = new JFrame();
        frame.setBounds(100, 100, 304, 317);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lbPageTitle = new JLabel("Editar título");
        lbPageTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbPageTitle.setBounds(12, 12, 290, 15);
        frame.getContentPane().add(lbPageTitle);

        JLabel lbId = new JLabel("Id");
        lbId.setBounds(22, 44, 70, 15);
        frame.getContentPane().add(lbId);
        JComboBox cbId = new JComboBox(titles.stream().map(t -> t.id).toArray());
        cbId.setBounds(60, 39, 149, 24);
        frame.getContentPane().add(cbId);

        JLabel lbName = new JLabel("Nome");
        lbName.setBounds(22, 83, 70, 15);
        frame.getContentPane().add(lbName);
        JTextField tfName = new JTextField(cbId.getSelectedIndex() >= 0 ?titles.get(cbId.getSelectedIndex()).name : "");
        tfName.setEnabled(cbId.getSelectedIndex() >= 0);
        tfName.setColumns(10);
        tfName.setBounds(125, 81, 155, 19);
        frame.getContentPane().add(tfName);

        JLabel lbQuantity = new JLabel("Quantidade");
        lbQuantity.setBounds(22, 143, 97, 15);
        frame.getContentPane().add(lbQuantity);
        JTextField tfQuantity = new JFormattedTextField(IntegerFormatter.getFormatter());
        tfQuantity.setText(cbId.getSelectedIndex() >= 0 ? (titles.get(cbId.getSelectedIndex()).quantity!=null?titles.get(cbId.getSelectedIndex()).quantity.toString(): ""): "");
        tfQuantity.setEnabled(cbId.getSelectedIndex() >= 0);
        tfQuantity.setColumns(10);
        tfQuantity.setBounds(125, 141, 155, 19);
        frame.getContentPane().add(tfQuantity);

        JLabel lbType = new JLabel("Tipo");
        lbType.setBounds(22, 114, 70, 15);
        frame.getContentPane().add(lbType);
        JComboBox cbType = new JComboBox(TitleTypesEnum.values());
        cbType.setSelectedItem(cbId.getSelectedIndex() >= 0 ?titles.get(cbId.getSelectedIndex()).type : TitleTypesEnum.BLURAY);
        cbType.setEnabled(cbId.getSelectedIndex() >= 0);
        cbType.setBounds(125, 109, 155, 24);
        frame.getContentPane().add(cbType);

        JLabel lbPeriod = new JLabel("Period. Max.");
        lbPeriod.setBounds(22, 172, 97, 15);
        frame.getContentPane().add(lbPeriod);
        JTextField tfPeriod = new JFormattedTextField(IntegerFormatter.getFormatter());
        tfPeriod.setText(cbId.getSelectedIndex() >= 0 ? (titles.get(cbId.getSelectedIndex()).maxPeriodOfRent != null ? titles.get(cbId.getSelectedIndex()).maxPeriodOfRent.toString() : "") : "");
        tfPeriod.setEnabled(cbId.getSelectedIndex() >= 0);
        tfPeriod.setColumns(10);
        tfPeriod.setBounds(125, 170, 155, 19);
        frame.getContentPane().add(tfPeriod);

        JButton btnDelete = new JButton("Deletar");
        btnDelete.setBounds(22, 199, 119, 25);
        frame.getContentPane().add(btnDelete);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(22, 236, 258, 25);
        frame.getContentPane().add(btnCancel);

        JButton btnUpdate = new JButton("Atualizar");
        btnUpdate.setBounds(161, 199, 117, 25);
        frame.getContentPane().add(btnUpdate);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        cbId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText(titles.get(cbId.getSelectedIndex()).name);
                tfQuantity.setText(titles.get(cbId.getSelectedIndex()).quantity.toString());
                cbType.setSelectedItem(titles.get(cbId.getSelectedIndex()).type);
                tfPeriod.setText(titles.get(cbId.getSelectedIndex()).maxPeriodOfRent.toString());
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbId.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(frame, "Alguns campos podem estar incorretos!", "Erro de validação", JOptionPane.WARNING_MESSAGE);
                }else if(titleService.delete(titles.get(cbId.getSelectedIndex()).id)){
                    JOptionPane.showMessageDialog(frame, "Título deletado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame, "Ocorreu um erro para deletar este título, tente novamente mais tarde", "Erro interno", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbId.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(frame, "Alguns campos podem estar incorretos!", "Erro de validação", JOptionPane.WARNING_MESSAGE);
                }else{
                    Title title = new Title(Integer.parseInt(cbId.getSelectedItem().toString()), tfName.getText(), cbType.getSelectedItem().toString(), Integer.parseInt(tfQuantity.getText()) , Integer.parseInt(tfPeriod.getText()));
                    if(titleService.update(title)){
                        JOptionPane.showMessageDialog(frame, "Título atualizado com sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(frame, "Ocorreu um erro para atualizar este título, tente novamente mais tarde", "Erro interno", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });
    }
}
