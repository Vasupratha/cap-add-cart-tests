package cap.hmrc.addcart.qa.pages;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;

public class CreateAccount_Page extends BasePage{
	
    public CreateAccount_Page() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}



	private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccount_Page.class);
    //uniform-id_gender1
	public static @FindBy(xpath = "//*[@id='uniform-id_gender1']") WebElement selectTitleMr;
	public static @FindBy(xpath = "//*[@id='id_gender2']") WebElement selectTitleMrs;
	public static @FindBy(xpath = "//*[@id='customer_firstname']") WebElement customerFirstNameTXT;
	public static @FindBy(xpath = "//*[@id='customer_lastname']") WebElement customerLastNameTXT;
	public static @FindBy(xpath = "//*[@id='email']") WebElement customeremailIDTXT;
	public static @FindBy(xpath = "//*[@id='passwd']") WebElement customerPasswordTXT;
	public static @FindBy(xpath = "//*[@id='uniform-days']") WebElement customerDobDay;
	public static @FindBy(xpath = "//*[@id='days']") WebElement customerDay;

	public static @FindBy(xpath = "//*[@id='uniform-months']") WebElement customerDobMonth;
	public static @FindBy(xpath = "//*[@id='uniform-years']") WebElement customerDobYear;
	
	
	public static @FindBy(xpath = "//*[@id='firstname']") WebElement addressFirstNameTXT;
	public static @FindBy(xpath = "//*[@id='lastname']") WebElement addressLastNameTXT;

	public static @FindBy(xpath = "//*[@id='address1']") WebElement buildingNoTXT;
	public static @FindBy(xpath = "//*[@id='address2']") WebElement streetNameTXT;
	public static @FindBy(xpath = "//*[@id='city']") WebElement cityNameTXT;
	public static @FindBy(xpath = "//*[@id='uniform-id_state']") WebElement stateNameTXT;	
	public static @FindBy(xpath = "//*[@id='id_state']/option[3]") WebElement stateDropDown;	
	
	//*[@id='id_state']/option[3]

	public static @FindBy(xpath = "//*[@id='postcode']") WebElement postcodeTXT;

	public static @FindBy(xpath = "//*[@id='phone_mobile']") WebElement mobileNoTXT;
	public static @FindBy(xpath = "//*[@id='submitAccount']") WebElement submitAccount;


	public static @FindBy(xpath = "//*[@id='alias']") WebElement addressAlias;
	
	public static @FindBy(xpath = "//*[@title='Information']") WebElement myPersonalInformationTitle;

	public static @FindBy(xpath = "//*[@title='Log me out']") WebElement signOutBTN;
	public static @FindBy(xpath = "//*[@id='SubmitLogin']") WebElement signInBTN;


	static Faker faker = new Faker(new Locale("en-US"));
	public static String emailIdEntered;
	public static String passwordEntered;


	public static String randomFirstNameGenerator() {
	    String generatedstring=RandomStringUtils.randomAlphabetic(10);
	    String randomFirstName = generatedstring;
	    LOGGER.info("Generted random name is" + randomFirstName);		   
	    return randomFirstName;

	}
	

	public static String randomLastNameGenerator() {
	    String generatedstring=RandomStringUtils.randomAlphabetic(10);
	    String randomLastName = generatedstring;
	    LOGGER.info("Generted random name is" + randomLastName);		   
	    return randomLastName;

	}
	
	public static String randomPasswordGenerator() {
	    String generatedstring=RandomStringUtils.randomAlphabetic(8);
	    String randowmPassword = generatedstring;
	    LOGGER.info("Generted random name is" + randowmPassword);		   
	    return randowmPassword;

	}
	
	public static String[] getAddress() {
		String streetName = faker.address().streetName();
		String buildNumber = faker.address().buildingNumber();
		String city = faker.address().city();
		String[] address = new String[4];
		
		address[0] = streetName;
		address[1] = buildNumber;
		address[2] = city;
		
		return address;
		
	}
	
	public static CreateAccount_Page selectTitleMr() throws IOException {	
		waitAndClickElement(selectTitleMr);		
		return new CreateAccount_Page();		
	}
	
	
	public static CreateAccount_Page selectTitleMrs() throws IOException {		
		waitAndClickElement(selectTitleMrs);
		return new CreateAccount_Page();		
	}
	
	public static CreateAccount_Page enterCustomerFirstName() throws Exception {
		String firstName = randomFirstNameGenerator();
		sendKeysToWebElement(customerFirstNameTXT,firstName);
		return new CreateAccount_Page();		
	}
	
	public static CreateAccount_Page enterCustomerLastName() throws Exception {
		String lastName = randomLastNameGenerator();
		sendKeysToWebElement(customerLastNameTXT,lastName);
		sendKeysTab(customerLastNameTXT);
		
		return new CreateAccount_Page();		
	}

	public static CreateAccount_Page enterCustomerPassword() throws Exception {
		String passwd = randomPasswordGenerator();
		LOGGER.info("Password generated is" + passwd);
		sendKeysToWebElement(customerPasswordTXT, passwd);
		sendKeysTab(customerPasswordTXT);

		return new CreateAccount_Page();		
	}

	public static CreateAccount_Page getEmailIdPassword() throws Exception {
		
		emailIdEntered = getElementValue(customeremailIDTXT);
		passwordEntered = getElementValue(customerPasswordTXT);
		
		return new CreateAccount_Page();		
		
	}
	
	public static CreateAccount_Page logSignInDetails() throws Exception {
		
		LOGGER.info("Logging Sign In Password entered on password text field is " + passwordEntered);
		LOGGER.info(" Logging Sign In Email entered on email text field " + emailIdEntered);
		return new CreateAccount_Page();		
		
	}
	
	
	
	
	
	
	public static CreateAccount_Page enterAddressFirstName() throws Exception {
		String firstName = getElementText(customerFirstNameTXT);
		sendKeysToWebElement(addressFirstNameTXT,firstName);
		sendKeysTab(addressFirstNameTXT);

		return new CreateAccount_Page();		
	}

	
	public static CreateAccount_Page enterAddressLastName() throws Exception {
		String lastName = getElementText(customerLastNameTXT);
		sendKeysToWebElement(addressLastNameTXT,lastName);
		//sendKeysTab(addressLastNameTXT);

		return new CreateAccount_Page();		
	}
	
	public static CreateAccount_Page enterAddress() throws Exception {
		String[] addressUS = getAddress();
		sendKeysToWebElement(buildingNoTXT,addressUS[1]);
		sendKeysToWebElement(streetNameTXT,addressUS[0]);
		sendKeysToWebElement(cityNameTXT,"Alabama");
		waitAndClickElement(stateDropDown);
		sendKeysToWebElement(postcodeTXT,"12345");

		return new CreateAccount_Page();		
	}

	public static CreateAccount_Page enterMobileNo() throws Exception {
		sendKeysToWebElement(mobileNoTXT,"0123456789");

		return new CreateAccount_Page();		
	}
	
	
	public static CreateAccount_Page clickRegisterButton() throws Exception {
		waitAndClickElement(submitAccount);
		return new CreateAccount_Page();		
	}
	
	
	public static CreateAccount_Page verifyMyPersonalInformationPresent() throws Exception {
		waitUntilElementVisible(myPersonalInformationTitle);
		return new CreateAccount_Page();		
	}
	
	
	public static CreateAccount_Page signOut() throws Exception {
		waitUntilElementVisible(signOutBTN);
		waitAndClickElement(signOutBTN);
		//waitUntilElementVisible(signInBTN);
		return new CreateAccount_Page();		
	}
	
	
	

}
