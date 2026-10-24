package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardPage {

    Page page;
    String baseURL;

    private static final String browseEvent = "Browse Events →";


    public DashboardPage(Page page, String baseURL) {

        this.page = page;
        this.baseURL = baseURL;

        assertThat(page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
    }


    public void waitForEventsToLoad()   {

        assertThat(page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(browseEvent))).isVisible();
    }
}
