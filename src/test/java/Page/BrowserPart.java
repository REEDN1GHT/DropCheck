package Page;

import Main.GUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static Main.GUI.driver1;
import static Main.GUI.wait1;

public class BrowserPart extends GUI {

    @FindBy(xpath = "//input[@id='login']")
    WebElement Login;
    @FindBy(xpath = "//input[@id='password']")
    WebElement Password;
    @FindBy(xpath= "//button[@class=\"btn flex-grow-1 w-25 btn-huge btn-primary e-btn btn-large\"]")
    WebElement ConfirmButton;


    public void TestGetURLUKT() {
            driver1.navigate().to(URLUKT);
        /*WebDriverWait newWait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        newWait.until(ExpectedConditions.elementToBeClickable(ConfirmButton));*/
    }
    public void TestGetURLKF() {
        driver1.navigate().to(URLKF);
    }

    public String CheckList(By str) {
        List<String> myValuess = driver1.findElements(str)
                .stream()
                .map(option -> option.getAttribute("innerText").trim())
                //.sorted()
                .collect(Collectors.toList());
        //if (Objects.equals(myValuess.get(0), "\u00a0")) {
        //    myValuess.set(0, " ");
        // }
        return myValuess.toString();
    }

}
