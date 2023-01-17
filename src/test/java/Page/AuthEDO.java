package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Resources.ConfigBuilder;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class AuthEDO {
    private WebDriver driver;
    private WebDriverWait wait;
    public static String EDOurl = ConfigBuilder.getproperty("EDOurl");
    public static String EDOPROD = "https://edo.fincom.gov.spb.ru/#/";


    @FindBy(css = ".btn.d-flex.mt-4.align-items-center.btn-primary.btn-huge")
    public WebElement buttonEnterMainPage;
    @FindBy(id = "login")
    public WebElement loginField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(css = ".btn.flex-grow-1.btn-primary.btn-huge")
    private WebElement buttonEnterAuthorization;
    @FindBy(id = "cabinet")
    WebElement role;
    @FindBy(css = "#cabinet-modal___BV_modal_footer_ >button:nth-child(1)")
    public WebElement buttonSignInCabinet;

    public AuthEDO(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    public int isElementPresented() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        List list = driver.findElements(By.xpath("//div[@class='mt-5 d-flex align-items-center flex-column justify-content-center']//button"));
        return list.size();
    }
    public void open() {
        driver.navigate().to(ConfigBuilder.getproperty("EDOurl"));
    }
    public void setButtonEnterMainPage(){
        buttonEnterMainPage.click();
    }
    public void setLoginField() {
        loginField.sendKeys(ConfigBuilder.getproperty("EDOlogin"));
    }
    public void setPasswordField() {
        passwordField.sendKeys(ConfigBuilder.getproperty("EDOpassword"));
    }
    public void setButtonEnterAuthorization() {
        buttonEnterAuthorization.click();
    }
    public void role() {
        var cabinetRole = new Select(role);
        cabinetRole.selectByVisibleText(ConfigBuilder.getproperty("EDOrole"));
    }
    public void setButtonSignInCabinet() {
        buttonSignInCabinet.click();
    }
    public void waitCabinet() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSignInCabinet));
    }
    public void authorization() {
        AuthEDO authEDO = new AuthEDO(driver, wait);
        authEDO.open();
        if(authEDO.isElementPresented()==1 )
        {
            authEDO.setButtonEnterMainPage();
            authEDO.setLoginField();
            authEDO.setPasswordField();
            authEDO.setButtonEnterAuthorization();
            authEDO.waitCabinet();
            authEDO.role();
            authEDO.setButtonSignInCabinet();
        } else if(authEDO.isElementPresented()>1) {
            driver.navigate().to("http://172.31.1.149/edo/main");
        }
    }
}
