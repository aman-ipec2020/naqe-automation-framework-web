package driver;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EventManager;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DataUtilityProvider;

import java.io.IOException;
import java.util.HashMap;

public class FrameworkBuilderDataDrivenTest extends BaseTest {

    @DataProvider(name="eventBookingData")
    public Object[][] eventBookingData() throws IOException {

        return DataUtilityProvider.getJsonDataToMap("/src/test/resources/testData/eventBookingData.json");
    }


    @Test(groups = {"smoke"}, dataProvider = "eventBookingData", description = "Create Multiple Events with .Json File - Book that event and verify if it's booked")
    public void DemoTest(HashMap<String, String> data)  {


        LoginPage loginPage = new LoginPage(page, base_URL);
        loginPage.loginToApplication();

        DashboardPage dashboardPage = new DashboardPage(page, base_URL);
        dashboardPage.waitForEventsToLoad();


        EventManager event = new EventManager(page, event_URL);
        event.createEvent(data);
        event.checkCreatedEvent();
//        event.deleteEvent(data);

        page.waitForTimeout(3000);
    }
}
