package cap.hmrc.addcart.qa.utils;

import io.restassured.RestAssured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.hmrc.addcart.qa.pages.MyStore_Page;

import static cap.hmrc.addcart.qa.utils.Constant.DEFAULT_USE_OF_DRESS_URL;
import static cap.hmrc.addcart.qa.utils.Constant.USE_OF_DRESS_URL;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadConfigFile.class);

	//private static final String USE_OF_BOOKING_URL = null;

    protected InputStream input = null;
    protected Properties prop = null;

    public ReadConfigFile() {
        try {
            final String configPropertiesPath = System.getProperty(Constant.CONFIG_PROPERTIES_PATH, Constant.DEFAULT_CONFIG_PROPERTIES_PATH);
            input = new FileInputStream(configPropertiesPath);
            prop = new Properties();
            prop.load(input);
            MyStore_Page.automationpracticeUrl = prop.getProperty(USE_OF_DRESS_URL, DEFAULT_USE_OF_DRESS_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return prop.getProperty("browser", "chrome");
    }
}
