package cap.hmrc.addcart.qa.pages;
import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import cap.hmrc.addcart.qa.pages.CreateAccount_Page;

import java.io.IOException;

public class SignIn_Page extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignIn_Page.class);

	public static @FindBy(xpath = "//*[@id='email_create']") WebElement emailTXTBOX;

	public static @FindBy(xpath = "//*[@id='SubmitCreate']") WebElement createAccountBTN;
	
	public static @FindBy(xpath = "//*[@id='email']") WebElement emailSignInTXT	;

	public static @FindBy(xpath = "//*[@id='passwd']") WebElement passwdSignInTXT	;

	public static @FindBy(xpath = "//*[@id='SubmitCreate']") WebElement createAccSubmitBTN;

	public static @FindBy(xpath = "//*[@id='SubmitLogin']") WebElement signInBTN;
	
	public static @FindBy(xpath = "//*[@class='login']") WebElement signInTopMenuBTN	;


	public SignIn_Page() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	static Faker faker = new Faker();

	public static String randomEmailIdGenerator() throws Exception {
	    String generatedstring=RandomStringUtils.randomAlphabetic(10);
	    String randomEmailId = generatedstring + "01@gmail.com";
	    LOGGER.info("Generted random email id is" + randomEmailId);	
	    getRandomEmailId(randomEmailId);
	    return randomEmailId;
		
	}
	
	public static void getRandomEmailId(String randEmailId) throws Exception {		
		String randomEmailId = randEmailId;
		
	}
	
	public static SignIn_Page enterRandomEmailId() throws Exception {
		String emailID = randomEmailIdGenerator();
		//String emailID = randomEmailId;
		LOGGER.info("Random Email Id to be entered is" + emailID);
	    waitUntilElementVisible(emailTXTBOX);
		sendKeysToWebElement(emailTXTBOX,emailID);
		
		return new SignIn_Page();		
	}
	

	public static SignIn_Page clickCreateAccount() throws Exception {
		clickElement(createAccountBTN);
		return new SignIn_Page();

	}
	
	public static SignIn_Page clickCreateAccountSubmitBTN() throws Exception {
	    waitUntilElementVisible(createAccSubmitBTN);

		clickElement(createAccSubmitBTN);
		return new SignIn_Page();

	}
	
	public static SignIn_Page enterLoginDetails() throws Exception {
		waitUntilElementVisible(signInTopMenuBTN);
//		clickElement(signInTopMenuBTN);
	    waitUntilElementVisible(emailSignInTXT);
	    waitUntilElementVisible(passwdSignInTXT);
	    sendKeysToWebElement(emailSignInTXT,CreateAccount_Page.emailIdEntered);
	    sendKeysToWebElement(passwdSignInTXT,CreateAccount_Page.passwordEntered);	
		clickElement(signInBTN);
		return new SignIn_Page();

	}
}


