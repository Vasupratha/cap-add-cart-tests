 package cap.hmrc.addcart.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.AssertJUnit;

import cap.hmrc.addcart.qa.utils.DriverFactoryBrowserStack;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class BasePage extends DriverFactoryBrowserStack {

    protected static WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    
    public BasePage() throws IOException {
        this.wait = new WebDriverWait(driver, 60);
        jsExecutor = ((JavascriptExecutor) driver);
    }
    

    public static void clickElement(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            LOGGER.trace("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");

        } catch (Exception e) {
            LOGGER.error("Unable to wait and click on WebElement, Exception: " + e.getMessage());
            AssertJUnit.fail(
                    "Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
        }
    }

    public static WebElement waitForWebElementContainingText(final By by, final String text) {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
        WebElement element = null;
        try {
            element = tempWait.until(driver -> {
                try {
                    final WebElement e = (driver.findElement(by));
                    if (e.isDisplayed()) LOGGER.trace("Found web element with text [{}]", e.getText());
                    return e.isDisplayed() && e.getText().contains(text) ? e : null;
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            });
            LOGGER.trace("Element is visible using By locator: " + "<" + by.toString() + ">");
            return element;
        } catch (Exception e) {
            final String message = "WebElement is NOT visible, using By.id: <" + by.toString() + "> expected [" + text + "] in MI search results table table.";
            LOGGER.error(message);
            AssertJUnit.fail(message + " Exception: " + e.getMessage());
            return element;
        }
    }

    public static void verifyElementText(WebElement element, String expectedTxt) {
        try {
            final String actualTxt = wait.until(ExpectedConditions.visibilityOf(element)).getText();
            final String escapedNewLineText = actualTxt.replace(System.getProperty("line.separator"), " ");
            Assert.assertEquals(expectedTxt, escapedNewLineText);
            LOGGER.trace("Element has got the right text value as expected");
        } catch (Exception e) {
            LOGGER.error("Unable to get the right text message" + e.getMessage());
            AssertJUnit.fail("Unable to get the correct Error Message");
        }
    }

    public static String getElementText(WebElement element) {
        try {
            final String actualTxt = wait.until(ExpectedConditions.visibilityOf(element)).getText();
            final String escapedNewLineText = actualTxt.replace(System.getProperty("line.separator"), " ");
            return escapedNewLineText;
        } catch (Exception e) {
            LOGGER.error("Unable to get the right text message" + e.getMessage());
            AssertJUnit.fail("Unable to get the correct Error Message");
            return null;
        }
        
    }
    
    
    public static String getElementValue(WebElement element) {
        try {
            
        	wait.until(ExpectedConditions.visibilityOf(element));
        	final String actualTxt = element.getAttribute("value");
            final String escapedNewLineText = actualTxt.replace(System.getProperty("line.separator"), " ");
            return escapedNewLineText;
        } catch (Exception e) {
            LOGGER.error("Unable to get the right text message" + e.getMessage());
            AssertJUnit.fail("Unable to get the correct Error Message");
            return null;
        }
        
    }
   

    public static void waitAndClickElement(WebElement webelement) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);

        try {
            wait.until(ExpectedConditions.visibilityOf(webelement)).click();

            LOGGER.trace("Successfully clicked on the WebElement: " + "<" + webelement.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to wait and click on WebElement, Exception: " + e.getMessage());
            AssertJUnit.fail(
                    "Unable to wait and click on the WebElement, using locator: " + "<" + webelement.toString() + ">");
        }

    }

    public static void waitAndClickElementLocated(final By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();

            LOGGER.trace("Successfully clicked on the WebElement: " + "<" + by.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to wait and click on WebElement, Exception: " + e.getMessage());
            AssertJUnit.fail(
                    "Unable to wait and click on the WebElement, using locator: " + "<" + by.toString() + ">");
        }

    }

    public static boolean waitUntilElementVisible(WebElement element) {
        // Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            LOGGER.trace("WebElement is visible using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            LOGGER.error("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
            LOGGER.error("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public static void findRadiosByNameAndSelectByValue(By locator, String radioValue) {
        List<WebElement> radios = driver.findElements(locator);
        for (WebElement radio : radios) {
            if (radio.getAttribute("value").equals(radioValue))
                (radio).click();
        }
    }

    public void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
        boolean clicked = false;
        // Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);

        while (!clicked) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                LOGGER.trace("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                LOGGER.error(
                        "Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
                AssertJUnit.fail("Unable to wait and click on the element using the By locator, element: " + "<"
                        + by.toString() + ">");
            }
        }
    }
    
    public static void sendKeysTab(WebElement element ) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(Keys.TAB);
            LOGGER.trace("Successfully sent the following keys:  to the following WebElement: "
                    + "<" + element.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to send the following keys:  the following WebElement: "
                    + "<" + element.toString() + ">");
            AssertJUnit.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }
    
    public static void selectFromDropDownList(WebElement element, String value) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            
            element.sendKeys(value);
            //Select selectValues = new Select(element);
            
            //selectValues.selectByValue(value);
            
        }catch(Exception e){
            LOGGER.error("Unable to select the values from the dropdown "
                    + "<" + element.toString() + ">");
            AssertJUnit.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());

        }

    	
    }
  
    
    public static void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(list)).click();
            Thread.sleep(500);
            list.sendKeys(textToSearchFor);
            Thread.sleep(500);
            list.sendKeys(Keys.ENTER);
            LOGGER.trace("Successfully sent the following keys: " + textToSearchFor + ", to the following WebElement: "
                    + "<" + list.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: "
                    + "<" + list.toString() + ">");
            AssertJUnit.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }

    public void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
        try {
            final WebDriverWait customWait = new WebDriverWait(driver, timeout);
            customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
            locator.click();
            LOGGER.trace("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"
                    + ", using a custom Timeout of: " + timeout);
        } catch (Exception e) {
            LOGGER.error("Unable to click on the WebElement, using locator: " + "<" + locator + ">"
                    + ", using a custom Timeout of: " + timeout);
            AssertJUnit.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public static void actionMoveAndClick(WebElement element) throws Exception {
        Actions ob = new Actions(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
            ob.moveToElement(element).click().build().perform();
            LOGGER.info("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<"
                    + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
            if (elementPresent == true) {
                ob.moveToElement(elementToClick).click().build().perform();
                LOGGER.info(
                        "(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: "
                                + "<" + element.toString() + ">");
            }
        } 
    }

    public static void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        try {
            LOGGER.info("Inside send keys try block");
            LOGGER.info("The text to be entered is " + textToSend);

            wait.until(ExpectedConditions.visibilityOf(element));
            LOGGER.info("After wait in send keys try block");

            //element.clear();
            actionShiftKeyRelease(element,textToSend);
            LOGGER.trace("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"
                    + element.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: "
                    + textToSend);
            AssertJUnit.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
        }
    }

    public static void actionShiftKeyRelease(WebElement element, final String textToSend) {
        Actions builder = new Actions(driver);
        Action releaseShiftKey = builder
                .moveToElement(element)
                .click()
                .keyDown(element, Keys.SHIFT)
                .keyUp(element, Keys.SHIFT)
                .sendKeys(element, textToSend)
                .build();

        releaseShiftKey.perform();
    }

    public static void scrollToElementByWebElementLocator(WebElement element) {

        try {
        	JavascriptExecutor jse = ((JavascriptExecutor) driver);
        	jse.executeScript("window.scrollTo(-400, 1000)");

            //wait.until(ExpectedConditions.visibilityOf(element));

            //LOGGER.info("Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
            AssertJUnit.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
        }
    }

    public static boolean waitUntilWebElementIsVisible(WebElement element) {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 60);
        try {
            tempWait.until(ExpectedConditions.visibilityOf(element));
            LOGGER.trace("WebElement is visible using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            LOGGER.error("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
            AssertJUnit.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public static WebElement waitUntilWebElementIsVisibleUsingByLocator(By element) {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 60);
        try {
            final WebElement element1 = tempWait.until(ExpectedConditions.visibilityOfElementLocated(element));
            LOGGER.trace("Element is visible using By locator: " + "<" + element.toString() + ">");
            return element1;
        } catch (Exception e) {
            LOGGER.error("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
            AssertJUnit.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return null;
        }
    }

    public boolean isElementClickable(WebElement element) {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 60);
        try {
            tempWait.until(ExpectedConditions.elementToBeClickable(element));
            LOGGER.trace("WebElement is clickable using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            LOGGER.error("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
            return false;
        }
    }

    public boolean waitUntilPreLoadElementDissapears(By element) {
        return this.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public String getCurrentURL() {
        try {
            String url = driver.getCurrentUrl();
            LOGGER.trace("Found(Got) the following URL: " + url);
            return url;
        } catch (Exception e) {
            LOGGER.error("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String waitForSpecificPage(String urlToWaitFor) {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 100);

        try {
            String url = driver.getCurrentUrl();
            tempWait.until(ExpectedConditions.urlMatches(urlToWaitFor));
            LOGGER.trace("The current URL was: " + url + ", " + "navigated and waited for the following URL: "
                    + urlToWaitFor);
            return urlToWaitFor;
        } catch (Exception e) {
            LOGGER.error("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    public void verifyPageURL(String pageURL) {
        try {
            String actualURL = driver.getCurrentUrl();
            Assert.assertEquals(pageURL, actualURL);
            LOGGER.trace("Expected page URL verified correctly");
        } catch (Exception e) {
            LOGGER.info("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
        }
    }

    public static boolean elementCollectionMatches(List expectedValues, Collection<String> elements) {
        return elements.equals(expectedValues);
    }

    public static List<String> getTableElementHeaderSet(WebElement element) {
        return getTableElementListByTag(element, By.tagName("th"));
    }

    public static List<String> getTableElementDataSet(WebElement element) {
        return getTableElementListByTag(element, By.tagName("td"));
    }

    public static List<String> getTableElementListByTag(WebElement element, By byTag) {
        LOGGER.trace("getTableElementListByTag start");
        final List<String> strings = findElements(element, byTag).stream().map(p -> p.getText()).collect(Collectors.toList());
        if (LOGGER.isTraceEnabled()) {
            strings.forEach(s -> LOGGER.trace("getTableElementListByTag got string [{}]", s));
        }
        LOGGER.trace("getTableElementListByTag end");
        return strings;
    }

    public static List<WebElement> findElements(final WebElement element, final By by) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        final List<WebElement> elements = element.findElements(by);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return elements;
    }

    public static void selectHighestPricedItem(final String by) throws Exception {
        List<String> addCartText = new ArrayList<String>();
        String  dressPriceTag;
        String[] finalDressPriceTag;
        double[] finalDressPrice;
        double max = 0.0;
    	List<WebElement> elements = driver.findElements(By.xpath(by));
    	String[] getTextFromWebElements = new String[elements.size()];
    	for(int i = 0; i < elements.size();i++) {  		
    		getTextFromWebElements[i] = elements.get(i).getText();
    		addCartText.add(getTextFromWebElements[i]);


    	}
    	addCartText.removeAll(Arrays.asList("", " ", null));
    	Object[] addCartValIntermediate = addCartText.toArray();
    	String[]  addCartValue = Arrays.copyOf(addCartValIntermediate, addCartValIntermediate.length,String[].class); 
    	//LOGGER.info("Add Cart text in String format is" + Arrays.toString(addCartValue));
    	dressPriceTag = Arrays.toString(addCartValue);
    	finalDressPriceTag = dressPriceTag.split(",");

    	double[] dressPriceinDouble = new double[finalDressPriceTag.length];
    	int index = 0;
    	for (int i = 0; i < finalDressPriceTag.length;i++) {
    		finalDressPriceTag[i] = finalDressPriceTag[i].substring(2,7);
    		dressPriceinDouble[i] = Double.parseDouble(finalDressPriceTag[i]);
    				//(finalDressPriceTag[i]);
    		
    		LOGGER.info("Substring final price tag in double is " + dressPriceinDouble[i]);		
	
    		if (max < dressPriceinDouble[i]) {
    			max = dressPriceinDouble[i];
    			index = i;
    		}
    		

    	}
    	
		LOGGER.info(" Maximimum dress price is : " + max + "index is : " + index);
		index += 1;
		//*[@class='product_list grid row']/li[2]/div/div[2]/div/a[1]
		final String xpathExpression = "//*[@class='product_list grid row']/li["+index+"]";

		WebElement dressHighestPrice = driver.findElement(By.xpath(xpathExpression));
		scrollToElementByWebElementLocator(dressHighestPrice);
		actionMoveAndClick(dressHighestPrice);
		waitAndClickElement(dressHighestPrice);
		//return getTextFromWebElements;
		
    }



    
    
    public static void textNotPresent(WebElement webElement, String textMsg) {
        int count = 0;
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        while(count < 50) {
            try {               
                count++;
                wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(webElement, textMsg)));
            }catch(StaleElementReferenceException e) {
                // we expect this error, we'll get element does not exist anymore and we want to ignore it
            }
            count++;
        }
    }
}
