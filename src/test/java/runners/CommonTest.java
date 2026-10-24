package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "@NewProductTest",
                plugin = {"pretty",
                        "html:target/cucumber-report.html",
                        "json:target/cucumber-report.json",
                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
                glue = {"hooks"},
                monochrome = true,
                publish = false)

public class CommonTest extends AbstractTestNGCucumberTests {
}
