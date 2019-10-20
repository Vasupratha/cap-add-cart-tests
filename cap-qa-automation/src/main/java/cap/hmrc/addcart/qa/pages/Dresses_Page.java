package cap.hmrc.addcart.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dresses_Page extends BasePage {

	public Dresses_Page() throws IOException {
		super();
		// TODO Auto-generated constructor stu
	}
	
	
	public static @FindBy(xpath = "//*[@class='content_price']") WebElement contentPrice;
	public static @FindBy(xpath = "//*[@title='Proceed to checkout']") WebElement proceedToCheckOut;
////*[@class='shopping_cart']/a/span
	public static @FindBy(xpath = "//*[@class='shopping_cart']/a/span") WebElement cartValueTXT;
	public static @FindBy(xpath = "//*[@name='Submit']") WebElement addCartBTN;

	public static Dresses_Page getPriceofDresses() throws Exception {
	
		String[] getPriceList;
		String xpathExpression =  "//*[@class='price product-price']";
		selectHighestPricedItem(xpathExpression);
		return new Dresses_Page();
		  
	}
	
	
	public static Dresses_Page clickProceedtoCheckOutButton() throws Exception {
		actionMoveAndClick(proceedToCheckOut);
		
		return new Dresses_Page();		  
	}
	
	public static Dresses_Page clickAddToCartButton() throws Exception {
		actionMoveAndClick(addCartBTN);
		
		return new Dresses_Page();		  
	}
	
	//getAddCartValue
	public static Dresses_Page getAddCartValue() throws Exception {
		verifyElementText(cartValueTXT, "1");
		return new Dresses_Page();
		  
	}
}
