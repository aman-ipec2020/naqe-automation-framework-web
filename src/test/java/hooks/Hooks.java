package hooks;

import com.playwright.automation.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@Playwright")
    public void startUp() throws IOException   {
        PlaywrightFactory.launchBrowser();
    }

    @After("@Playwright")
    public void closeDown() throws IOException   {
        PlaywrightFactory.shutdownBrowser();
    }
}
