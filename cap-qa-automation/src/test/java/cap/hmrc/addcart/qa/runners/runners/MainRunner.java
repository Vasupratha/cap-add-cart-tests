package cap.hmrc.addcart.qa.runners.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
    features = {
        "src/test/resources/features-addCart/addCart.feature"
    },
    glue = {"cap/hmrc/addcart/qa/steps"},
    monochrome = true,
    tags = {},
    plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json", "com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
)
public class MainRunner extends AbstractTestNGCucumberTests {
         
}
