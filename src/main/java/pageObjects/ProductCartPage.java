package pageObjects;

import Globals.WebGlobals;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCartPage extends WebGlobals {
    @FindBy(xpath = "//input[@id='buy-now-button']")
    public WebElement buyNowButton ;

    public ProductCartPage(WebDriver driver)
            throws ConfigurationException {
        PageFactory.initElements(driver, this);
    }
    public void clickBuyNowButton() throws Exception{
        click(buyNowButton);
        System.out.print("Buy Now button clicked successfully");
        VerifyPageReady(30);
        Thread.sleep(5000);
    }
}
