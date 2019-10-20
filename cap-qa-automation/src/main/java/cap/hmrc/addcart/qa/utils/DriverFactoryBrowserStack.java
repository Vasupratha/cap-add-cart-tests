package cap.hmrc.addcart.qa.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.hmrc.addcart.qa.pages.CreateAccount_Page;
import cap.hmrc.addcart.qa.pages.Dresses_Page;
import cap.hmrc.addcart.qa.pages.MyStore_Page;
import cap.hmrc.addcart.qa.pages.SignIn_Page;
import io.github.bonigarcia.wdm.WebDriverManager;



public class DriverFactoryBrowserStack {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactoryBrowserStack.class);

    public static WebDriver driver;
    public static MyStore_Page myStorePage;
    public static SignIn_Page signInPage;
    public static CreateAccount_Page createAccountPage;
    public static Dresses_Page dressesPage;

    public static final String USERNAME = "testuserone";
    public static final String AUTOMATE_KEY = "xxxxx";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@xx-xxx.xxx.com/xx/xx";
    public static final String ZALENIUM_URL = "http://localhost:4444/wd/hub";
    public static WebDriver getDriver() {
        
        try {
            // Read Config
            ReadConfigFile file = new ReadConfigFile();
            String browserName = file.getBrowser();

            switch (browserName) {

                case "BrowserStackchrome":
                    // code
                    if (null == driver) {
                        
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("os", "Windows");
                        caps.setCapability("os_version", "10");
                        caps.setCapability("browser", "Chrome");
                        caps.setCapability("browser_version", "62.0");
                        caps.setCapability("browserstack.local", "false");
                        caps.setCapability("browserstack.debug", "true");
                        LOGGER.info("Instantiating chrome driver");
                        driver = new RemoteWebDriver(new URL(URL), caps);
                        LOGGER.info("Finished instantiating chrome driver");
                        driver.manage().window().maximize();
                    }
                    break;

                case "BrowserStackie10":
                // code
                    if (null == driver) {
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("os", "Windows");
                        caps.setCapability("os_version", "10");
                        caps.setCapability("browser", "IE");
                        caps.setCapability("browser_version", "11.0");
                        caps.setCapability("browserstack.local", "false");
                        LOGGER.info("Instantiating IE 10 driver");
                        driver = new RemoteWebDriver(new URL(URL), caps);
                        LOGGER.info("Finished instantiating IE10 driver");
                        driver.manage().window().maximize();
                }
                break;
            
                case "BrowserStackie11":
                // code
                    if (null == driver) {
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("os", "Windows");
                        caps.setCapability("os_version", "10");
                        caps.setCapability("browser", "IE");
                        caps.setCapability("browser_version", "11.0");
                        caps.setCapability("browserstack.local", "false");
                        LOGGER.info("Instantiating IE 11 driver");
                        driver = new RemoteWebDriver(new URL(URL), caps);
                        LOGGER.info("Finished instantiating IE11 driver");
                        driver.manage().window().maximize();
                    }
                break;

                case "zalenium":
                    /*
                    see https://opensource.zalando.com/zalenium/
                    see http://localhost:4444/grid/admin/live for live stream of the browser
                    see http://localhost:4444/dashboard/ for recordings of previous runs
                    see http://localhost:4444/wd/hub/status for status report of the zalenium hub
                    To run with zalenium you will need to run it locally with this bash script...:START
                    # Pull docker-selenium
                    docker pull elgalu/selenium
                    # Pull Zalenium
                    docker pull dosel/zalenium
                    docker container rm zalenium
                    docker run --rm -ti --name zalenium -p 4444:4444 \
                          -v /var/run/docker.sock:/var/run/docker.sock \
                          -v /tmp/videos:/home/seluser/videos \
                          --privileged dosel/zalenium start
                    # might need...: -e DOCKER=17.06.2-ce
END
                     */
                    if (null == driver) {
                        DesiredCapabilities caps = DesiredCapabilities.chrome();
                        caps.setCapability("idleTimeout", 180);

                        LOGGER.info("Instantiating zalenium driver");
                        driver = new RemoteWebDriver(new URL(ZALENIUM_URL), caps);

                        LOGGER.info("Finished instantiating zalenium driver");
                        driver.manage().window().maximize();
                    }
                break;

                case "chrome":
                    // code
                    if (null == driver) {
                        System.setProperty("wdm.chromeDriverVersion", "71.0.3578.33");
//                        System.setProperty("wdm.chromeDriverVersion", "2.45");
                        // CHROME OPTIONS
                        final ChromeOptions options = new ChromeOptions();
                        final String downloadFilepath = Constant.TEMP_FOLDER;
                        final Map<String, Object> chromePrefs = new HashMap<>();
                        chromePrefs.put("download.default_directory", downloadFilepath);
                        options.setExperimentalOption("prefs", chromePrefs);

                        WebDriverManager.chromedriver().setup();
                        LOGGER.info("Instantiating chrome driver");
                        driver = new ChromeDriver(options);
                        LOGGER.info("Finished instantiating chrome driver");
                        driver.manage().window().maximize();
                    }
                    break;
                
            }
        } catch (Throwable e) {
            LOGGER.error("Unable to load browser: " + e.getMessage());
        } finally {
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            myStorePage = PageFactory.initElements(driver, MyStore_Page.class);
            signInPage = PageFactory.initElements(driver, SignIn_Page.class);
            createAccountPage = PageFactory.initElements(driver, CreateAccount_Page.class);
            dressesPage = PageFactory.initElements(driver, Dresses_Page.class);

        }
        return driver;
    }

}
