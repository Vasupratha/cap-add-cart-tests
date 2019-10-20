package cap.hmrc.addcart.qa.steps;

import cap.hmrc.addcart.qa.pages.SignIn_Page;
import cucumber.api.java.en.Given;

public class SignInStepDef {

	
	@Given("^user creates an account by entering email id$")
	public void user_creates_an_account_by_entering_email_id() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		SignIn_Page.enterRandomEmailId();
		SignIn_Page.clickCreateAccount();
	}
	
	@Given("^user sign in to the application$")
	public void user_sign_in_to_the_application() throws Throwable {
		SignIn_Page.enterLoginDetails();
	}
}
