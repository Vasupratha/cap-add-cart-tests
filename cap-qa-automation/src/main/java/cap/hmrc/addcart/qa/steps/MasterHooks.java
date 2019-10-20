package cap.hmrc.addcart.qa.steps;

import cap.hmrc.addcart.qa.utils.DriverFactoryBrowserStack;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class MasterHooks extends DriverFactoryBrowserStack {
    
    @Before
    public void setup() {
        driver = getDriver();
    }
    
    @After
    public void tearDown() {
        if(driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }

}
