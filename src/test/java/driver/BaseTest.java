package driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class BaseTest {

    public Playwright playwright;
    public Browser browser;
    public Page page;
    public String base_URL;
    public String event_URL;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {

        Properties properties = new Properties();

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        String env = System.getProperty("Env") != null ? System.getProperty("Env") : "config";


        properties.load(new FileInputStream("src/test/resources/config/" + env + ".properties"));
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless"));

        base_URL = properties.getProperty("base.url");
        event_URL = properties.getProperty("event.url");

        playwright = Playwright.create();

        if ("firefox".equalsIgnoreCase(browserName))
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));

        else if ("safari".equalsIgnoreCase(browserName))
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));

        else
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));

        page = browser.newPage();
        page.navigate(base_URL);

        System.out.println("Page Title : " + page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
    }

    @AfterMethod
    public void tearDown()  {


    }


    @Test
    public void AdvancedTest()  {

    }

}
