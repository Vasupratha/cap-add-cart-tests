package cap.hmrc.addcart.qa.utils;

import java.io.File;

public class Constant {

    private static final String PATH = new File(".").getPath();
    public static String SIG_URL;
    public static String TEMP_FOLDER;
    public static String SFT_FOLDER;

    /**Config Properties file **/
    public final static String CONFIG_PROPERTIES_PATH = "config.properties.path";
    public final static String DEFAULT_CONFIG_PROPERTIES_PATH = PATH + "/src/test/resources/config-myStore.properties";

    public final static String CHROME_DRIVER_ABSOLUTE_PATH = "chrome.driver.absolute.path";
    public final static String DEFAULT_CHROME_DRIVER_ABSOLUTE_PATH = PATH + "/src/test/resources/other/chromedriver";

    //public static final String USE_OF_SERVICE_URL = "use.of.service.url";
    public static final String USE_OF_DRESS_URL = "use.of.addcart.url";
    public static final String DEFAULT_USE_OF_DRESS_URL = "http://automationpractice.com/index.php";
}


