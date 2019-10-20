package cap.hmrc.addcart.qa.steps;

import cap.hmrc.addcart.qa.pages.CreateAccount_Page;
import cucumber.api.java.en.Given;

public class CreateAccountStepDef {
	
	@Given("^user enters details to create an account$")
	public void user_enters_details_to_create_an_account() throws Throwable {
		CreateAccount_Page.selectTitleMr();
		CreateAccount_Page.enterCustomerFirstName();
		CreateAccount_Page.enterCustomerLastName();
		CreateAccount_Page.enterCustomerPassword();
		CreateAccount_Page.enterAddressFirstName();
		CreateAccount_Page.enterAddressLastName();
		CreateAccount_Page.enterAddress();
		CreateAccount_Page.enterMobileNo();
		CreateAccount_Page.getEmailIdPassword();
		CreateAccount_Page.clickRegisterButton();
		CreateAccount_Page.verifyMyPersonalInformationPresent();
	}

	@Given("^get the login details of the user$")
	public void get_the_login_details_of_the_user() throws Throwable {
		//CreateAccount_Page.getEmailIdPassword();
		CreateAccount_Page.logSignInDetails();
	}
	
	@Given("^user sign outs from the appplication$")
	public void user_sign_outs_from_the_appplication() throws Throwable {
		CreateAccount_Page.signOut();
	}

	@Given("^get the login details of the user to login to the application$")
	public void get_the_login_details_of_the_user_to_login_to_the_application() throws Throwable {
	}


}
