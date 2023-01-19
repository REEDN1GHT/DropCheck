package Main;


import Page.BD;
import Page.SQL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static Page.SQL.RLyear;


public class GUI extends JFrame {
    public static JRadioButton sTestKF = new JRadioButton("Тест КФ");
    public static JRadioButton sTestUKT = new JRadioButton("Тест УКТ");
    public static JRadioButton y22 = new JRadioButton("22 год");
    public static JRadioButton y23 = new JRadioButton("23 год");
    private JLabel Llogin = new JLabel("Логин");
    private JTextField Login = new JTextField("7807018464", 100);
    private JLabel Lpassword = new JLabel("Пароль");
    private JTextField Password = new JTextField("7807018464", 100);
    private JLabel LUrlForm = new JLabel("Ссылка на форму");
    private JTextField urlForm = new JTextField("http://172.31.1.149/agreement/main/documents/form", 500);
    private JLabel LXpathForm = new JLabel("Локатор");
    private JTextField XpathField = new JTextField("//*[@id=\"undefined-kbk-7807018464\"]/div[3]/ul/li", 1000);
    private JLabel LSqlText = new JLabel("SQL Запрос");
    private JTextField SqlText = new JTextField("SqlText", 1000);
    private JLabel LSqlColum = new JLabel("Имя столбца");
    private JTextField SqlColum = new JTextField("Год", 1000);
    private JButton ConfirmButton = new JButton("Выполнить");

    public static String SQLSTATIC;
    public static String SQLSTATICCOLUM;
    public static String staticLogin;
    public static String staticPassword;
    public static String staticUrlForm;
    public static String staticXpathObject;
    public static String URLKF = "http://172.31.1.149/agreement/login";
    public static String URLUKT = "http://10.69.0.212:5300/agreement/login";
    public By ByLogin = By.xpath("//input[@id='login']");
    public By ByPassword = By.xpath("//input[@id='password']");
    public By ByConfirmButton = By.xpath("//button[@class='btn flex-grow-1 w-25 btn-huge btn-primary e-btn btn-large']");


    public static WebDriver driver1;
    public static WebDriverWait wait1;

        public GUI () {
            super("DropCheck");
            this.setBounds(200, 200, 800, 200);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(3, 3, 2, 2));
            container.add(Llogin);
            container.add(Lpassword);
            container.add(LUrlForm);
            container.add(LXpathForm);
            container.add(LSqlText);
            container.add(LSqlColum);
            container.add(Login);
            container.add(Password);
            container.add(urlForm);
            container.add(XpathField);
            container.add(SqlText);
            container.add(SqlColum);

            ButtonGroup RadioGroup1 = new ButtonGroup();
            RadioGroup1.add(sTestKF);
            RadioGroup1.add(sTestUKT);
            container.add(sTestKF);
            container.add(sTestUKT);
            sTestKF.setSelected(true);
            ButtonGroup RadioGroup2 = new ButtonGroup();
            RadioGroup2.add(y22);
            RadioGroup2.add(y23);
            container.add(y22);
            container.add(y23);
            y22.setSelected(true);
            ConfirmButton.addActionListener(new ButtonEvent () );
            container.add(ConfirmButton);
        }
    static void saveToFile(String text) throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("text.txt"));
            out.writeObject(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        class ButtonEvent extends BD implements ActionListener {
            public List<String> CheckList(By str) {
                List<String> myValuess = driver1.findElements(str)
                        .stream()
                        .map(option -> option.getAttribute("innerText").trim())
                        //.sorted()
                        .collect(Collectors.toList());

                //if (Objects.equals(myValuess.get(0), "\u00a0")) {
                //    myValuess.set(0, " ");
                // }
                System.out.println("myValuess" + myValuess);
                return myValuess;
            }
            public SQL requestSQL = new SQL();
            public void actionPerformed (ActionEvent e) {
                System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
                driver1 = new ChromeDriver();
                wait1 = new WebDriverWait(driver1, Duration.ofSeconds(5));
                driver1.manage().window().maximize();
                driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                staticLogin = Login.getText();
                staticPassword = Password.getText();
                staticUrlForm = urlForm.getText();
                staticXpathObject = XpathField.getText();
                SQLSTATICCOLUM = SqlColum.getText();
                SQLSTATIC = SqlText.getText();
                if (sTestUKT.isSelected()) {
                    driver1.navigate().to(URLUKT);
                    System.out.println('1');
                    } else {
                    driver1.navigate().to(URLKF);
                    System.out.println('2');
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                driver1.findElement(ByLogin).sendKeys(staticLogin);
                driver1.findElement(ByPassword).sendKeys(staticPassword);
                driver1.findElement(ByConfirmButton).click();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                driver1.navigate().to(staticUrlForm);
                try {
                    Thread.sleep(35000);
                } catch (InterruptedException ex2) {
                    throw new RuntimeException(ex2);
                }
                By ByXpath = By.xpath(staticXpathObject + "[@class='multiselect__element']");
                List <String> ResultEDO = CheckList(ByXpath);
                List <String> ResultSQL = requestSQL.SQLText();
                int SizeEDO = ResultEDO.size();
                int SizeSQL = ResultSQL.size();
                driver1.quit();
                boolean isEqual = (ResultEDO.equals(ResultSQL));
                String message = "";
                message += (sTestKF.isSelected() ? "Контур Тест КФ " : "Контур Тест УКТ " + "\n" );
                message += (y22.isSelected() ? "22 " : "23 " + "\n");
                if (isEqual) {
                    message += "Списки идентичны";
                } else {
                    if (SizeSQL>SizeEDO) {
                        ResultSQL.removeAll(ResultEDO);
                        System.out.println("ResultSQL " + "\n" + ResultSQL + "\n" + "Size " + ResultSQL.size() );
                        message += "Списки различаются" + "ЭДО " + SizeEDO + "АИС БП " + SizeSQL + "\n";
                        message += "Кол-во значений отсутствующих в ЭДО " + ResultSQL.size() + "\n";
                        message += "Список значений" + "\n";
                        message += ResultSQL + "\r\n";

                    } else {
                        ResultEDO.removeAll(ResultSQL);
                        System.out.println("ResultSQL " + "\n" + ResultEDO + "\n" + "Size " + ResultEDO.size());
                        message += "Списки различаются" + "ЭДО " + SizeEDO + "АИС БП " + SizeSQL + "\n";
                        message += "Кол-во значений отсутствующих в АИС-БП " + ResultEDO.size() + "\n";
                        message += "Список значений" + "\n";
                        message += ResultEDO + "\n";
                    }
                }
                JOptionPane.showConfirmDialog(null, message.replaceAll(",", "\n"), "Result", JOptionPane.DEFAULT_OPTION);
                SizeEDO = 0;
                SizeSQL = 0;
                ResultEDO.clear();
                ResultSQL.clear();
                JFrame win = new JFrame();
                win.revalidate();

            }

         }
}
