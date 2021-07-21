package pageObjects;

import Globals.WebGlobals;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends WebGlobals {

    @FindBy(xpath = "//a[@id='nav-hamburger-menu']")
    public WebElement hamburgerMenuItem ;

    @FindBy(xpath = "//a[@class='hmenu-item']//div[text()='Kindle']")
    public WebElement kindleMenuItem ;

    @FindBy(xpath = "//a[@class='hmenu-item'][text()='Kindle']")
    public WebElement kindleReaderMenuItem ;

    public HomePage(WebDriver driver)
            throws ConfigurationException {
        PageFactory.initElements(driver, this);
    }
    public void clickHamburgerMenu()throws Exception{
        click(hamburgerMenuItem);
        System.out.print("Hamburger menu item clicked successfully");
        Thread.sleep(2000);
    }
    public void clickKindleMenuItem()throws Exception{
        Wait(kindleMenuItem,20);
        click(kindleMenuItem);
        System.out.print("Kindle menu item clicked successfully");

    }
    public void clickKindleMenuItemUnderKindleReader()throws Exception{
        Wait(kindleReaderMenuItem,20);
        click(kindleReaderMenuItem);
        System.out.print("Kindle menu item clicked successfully");
        VerifyPageReady(30);
    }




}
