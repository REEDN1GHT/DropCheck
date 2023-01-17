package Main;


import Page.BD;
import Page.BrowserPart;
import Page.SQL;
import Page.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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


public class GUI extends JFrame {
    public static JRadioButton sTestKF = new JRadioButton("Тест КФ");
    public static JRadioButton sTestUKT = new JRadioButton("Тест УКТ");
    public static JRadioButton y22 = new JRadioButton("22 год");
    public static JRadioButton y23 = new JRadioButton("23 год");
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
            public SQL requestSQL = new SQL();
            public void actionPerformed (ActionEvent e) {
                BrowserPart BP = new BrowserPart();
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
                    /*if (sTestKF.isSelected()) {
                        BP.TestGetURLKF();
                        System.out.println('1');
                    } else {
                        BP.TestGetURLUKT();
                        System.out.println('2');
                    }
                driver1.quit();*/
                //driver1.findElement(ByLogin).sendKeys(staticLogin);
                //driver1.findElement(ByPassword).sendKeys(staticPassword);
                //driver1.findElement(ByConfirmButton).click();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
                /*driver1.navigate().to(staticUrlForm);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
                String ResultEDO = BP.CheckList(By.id(staticXpathObject));
                driver1.quit();*/
                String message = "";
                message += (sTestKF.isSelected() ? "Тест КФ" : "Тест УКТ");
                message += (y22.isSelected() ? "22" : "23");
                //message += ResultEDO;
                //message += requestSQL.SQLText();
                JOptionPane.showConfirmDialog(null, message, "Result", JOptionPane.DEFAULT_OPTION);

            }

         }
}
