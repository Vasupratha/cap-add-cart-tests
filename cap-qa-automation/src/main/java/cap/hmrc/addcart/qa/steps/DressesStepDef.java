package cap.hmrc.addcart.qa.steps;

import cap.hmrc.addcart.qa.pages.Dresses_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DressesStepDef {
	
	
    @Given("^user selects the dress with highest price tag$")
    public void user_selects_the_dress_with_highest_price_tag() throws Throwable {
    	Dresses_Page.getPriceofDresses();
    	Dresses_Page.clickAddToCartButton();
    	Dresses_Page.clickProceedtoCheckOutButton();
    }


@Then("^checks if the product is added to the cart$")
public void checks_if_the_product_is_added_to_the_cart() throws Throwable {   

	Dresses_Page.getAddCartValue();


}

}
