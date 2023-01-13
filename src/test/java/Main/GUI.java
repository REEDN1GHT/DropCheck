package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class GUI extends JFrame {
    private JRadioButton sTestKF = new JRadioButton("Тест КФ");
    private JRadioButton sTestUKT = new JRadioButton("Тест УКТ");
    private JLabel Llogin = new JLabel("Логин");
    private JTextField Login = new JTextField("Login", 100);
    private JLabel Lpassword = new JLabel("Пароль");
    private JTextField Password = new JTextField("Password", 100);
    private JLabel LUrlForm = new JLabel("Ссылка на форму");
    private JTextField urlForm = new JTextField("urlForm", 500);
    private JLabel LXpathForm = new JLabel("Локатор");
    private JTextField XpathField = new JTextField("XpathField", 1000);
    private JLabel LSqlText = new JLabel("SQL Запрос");
    private JTextField SqlText = new JTextField("SqlText", 1000);
    private JButton ConfirmButton = new JButton("Выполнить");

        public GUI () {
            super("DropCheck");
            this.setBounds(200, 200, 600, 200);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(3, 3, 2, 2));
            container.add(Llogin);
            container.add(Lpassword);
            container.add(LUrlForm);
            container.add(LXpathForm);
            container.add(LSqlText);
            container.add(Login);
            container.add(Password);
            container.add(urlForm);
            container.add(XpathField);
            container.add(SqlText);

            ButtonGroup RadioGroup = new ButtonGroup();
            RadioGroup.add(sTestKF);
            RadioGroup.add(sTestUKT);
            container.add(sTestKF);
            sTestKF.setSelected(true);
            container.add(sTestUKT);
            ConfirmButton.addActionListener(new ButtonEvent () );
            container.add(ConfirmButton);

        }
    static void saveToFile(String text) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("text.txt"));
            out.writeObject(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        class ButtonEvent implements ActionListener {
            public void actionPerformed (ActionEvent e){
                String message = "";
                message += urlForm.getText() + "\n";
                message += XpathField.getText() + "\n";
                message += SqlText.getText() + "\n";
                message += (sTestKF.isSelected() ? "Тест КФ" : "Тест УКТ");
                saveToFile(urlForm.getText());
                saveToFile(XpathField.getText() + "\n");
                saveToFile(SqlText.getText() + "\n");
                saveToFile(sTestKF.getText() + "\n");
                JOptionPane.showConfirmDialog(null, message, "Result", JOptionPane.PLAIN_MESSAGE);

            }

         }
}
