package Steps;

import Globals.WebGlobals;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductCartPage;

import java.util.concurrent.TimeUnit;

public class testAmazonKindle extends WebGlobals {
    String hostUrl;
    String http = "https://";
    String url;
    HomePage homePage;

    @Given("open amazon site")
    public void open_amazon_site()throws Exception {
        browserSelect();
        browserMax();
        browserNav("https://www.amazon.ca");
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().deleteAllCookies();
        System.out.print("Website opened");
        VerifyPageReady(30);
        Thread.sleep(5000);

    }

    @When("Click on hamburger menu")
    public void click_on_hamburger_menu() throws Exception{
        homePage = new HomePage(browser);
        homePage.clickHamburgerMenu();
    }

    @When("Click kindle menu item")
    public void click_kindle_menu_item() throws Exception {
        homePage = new HomePage(browser);
        homePage.clickKindleMenuItem();
    }

    @When("click kindle item under kindle reader menu")
    public void click_kindle_item_under_kindle_reader_menu() throws Exception{
        homePage = new HomePage(browser);
        homePage.clickKindleMenuItemUnderKindleReader();
    }

    @When("click on buy now")
    public void click_on_buy_now() throws Exception{
        ProductCartPage productCartPage = new ProductCartPage(browser);
        productCartPage.clickBuyNowButton();
    }

    @Then("verify user asked for email or mobile number")
    public void verify_user_asked_for_email_or_mobile_number() throws Exception{
        LoginPage loginPage = new LoginPage(browser);
        Assert.assertTrue("Sign in header found",verifyElementDisplayed(loginPage.signInHeader,10));
        Assert.assertTrue("Email or phone number found",verifyElementDisplayed(loginPage.emailPhoneNumberLabel,10));
        browserClose();

    }
    

}
