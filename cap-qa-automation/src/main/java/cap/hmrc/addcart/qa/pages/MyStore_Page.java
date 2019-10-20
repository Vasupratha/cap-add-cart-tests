package cap.hmrc.addcart.qa.pages;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import junit.framework.AssertionFailedError;

//import uk.gov.dwp.qa.pages.MIViewer_Page.BooleanResponse;



public class MyStore_Page extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyStore_Page.class);

	public static String automationpracticeUrl;

	public static @FindBy(xpath = "//*[@id='header_logo']") WebElement headerLogo;
	public static @FindBy(xpath = "//*[@id='header_logo']") WebElement signIn;
	public static @FindBy(xpath = "//*[@class='login']") WebElement logIn;
	public static @FindBy(xpath = "//*[@id='email_create']") WebElement createAcctBTN;
	public static @FindBy(xpath = "//*[@id='block_top_menu']/ul/li[2]") WebElement dressesTopMenuBTN;
	public static @FindBy(xpath = "//*[@class='product-count']") WebElement productCountTXT;
	
//
	public MyStore_Page() throws IOException {
		super();
	}


    public static MyStore_Page getBookingURL() throws Exception {    	
        getDriver().get(automationpracticeUrl);
        return new MyStore_Page();
    }
    
    public static MyStore_Page waitForPageLoad() throws Exception {    	
        waitUntilElementVisible(headerLogo);
		return new MyStore_Page();
    }
    
   
    public static MyStore_Page clickOnSignInBTN() throws Exception {    	
        waitUntilElementVisible(logIn);
        waitAndClickElement(logIn);
        waitUntilElementVisible(createAcctBTN);
		return new MyStore_Page();
    }
    
    public static MyStore_Page clickOnDressesTopMenu() throws Exception {    	
        waitUntilElementVisible(dressesTopMenuBTN);
        actionMoveAndClick(dressesTopMenuBTN);
        //waitAndClickElement(dressesTopMenuBTN);
        waitUntilElementVisible(productCountTXT);
		return new MyStore_Page();
    }
    
}


    
    
    

