package pageObjects;

import Globals.WebGlobals;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends WebGlobals {

    @FindBy(xpath = "//h1[contains(text(),'Sign-In')]")
    public WebElement signInHeader;

    @FindBy(xpath = "//label[contains(text(),'E-mail address or mobile phone number')]")
    public WebElement emailPhoneNumberLabel;

    public LoginPage(WebDriver driver)
            throws ConfigurationException {
        PageFactory.initElements(driver, this);
    }

}
