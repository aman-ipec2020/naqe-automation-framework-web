package driver;

import org.testng.annotations.Test;
import pages.EventManager;
import pages.DashboardPage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FrameworkBuilderTest extends BaseTest{

    @Test(groups = {"framework"}, description = "Create Single Events - Book that event and verify if it's booked")
    public void DemoTest()  {


        LoginPage loginPage = new LoginPage(page, base_URL);
        loginPage.loginToApplication();


        DashboardPage dashboardPage = new DashboardPage(page, base_URL);
        dashboardPage.waitForEventsToLoad();


        EventManager event = new EventManager(page, event_URL);
        event.createEvent();
        event.checkCreatedEvent();
        event.deleteEvent();

        page.waitForTimeout(1000);
    }
}
