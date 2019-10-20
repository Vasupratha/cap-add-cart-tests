package cap.hmrc.addcart.qa.steps;

import cap.hmrc.addcart.qa.pages.MyStore_Page;
import cucumber.api.java.en.Given;

public class MyStoreStepDef {


	@Given("^user access the automation practice site$")
	public void user_access_the_automation_practice_site() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MyStore_Page.getBookingURL();
		MyStore_Page.waitForPageLoad();

	}

	@Given("^user clicks on the sign in button$")
	public void user_clicks_on_the_sign_in_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MyStore_Page.clickOnSignInBTN();
		
	}
	

    @Given("^clicks on Dresses top menu$")
    public void clicks_on_Dresses_top_menu() throws Throwable {
		MyStore_Page.clickOnDressesTopMenu();

    }

	
}
