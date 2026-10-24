package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;

import java.util.HashMap;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EventManager {


    Page page;
    String event_URL;

    private static final String eventTitle = "#event-title-input";
    private static final String eventDescription = "#admin-event-form textarea";
    private static final String eventCategory = "Category";
    private static final String eventCity = "City";
    private static final String eventVenue = "Venue";
    private static final String eventDateAndTime = "Event Date & Time";
    private static final String eventPrice = "Price";
    private static final String eventTotalSeats = "Total Seats";
    private static final String eventImageURL = "Image URL (optional)";
    private static final String addEventBtn = "+ Add Event";

    public String[] eventData = {"QA Summit Aman Pradhan", "QA Summit Aman Pradhan", "Conference",
            "Delhi", "JLN Stadium", "2026-10-31T11:00", "7000", "1000"};


    public EventManager(Page page, String event_URL)   {
        this.page = page;
        this.event_URL = event_URL;
    }

    public void createEvent()   {

        page.navigate(event_URL);


        page.locator(eventTitle).fill(eventData[0]);
        page.locator(eventDescription).fill(eventData[1]);

        //  Selecting dropdown Value
        page.getByLabel(eventCategory).selectOption(eventData[2]);
        page.getByLabel(eventCity).fill(eventData[3]);
        page.getByLabel(eventVenue).fill(eventData[4]);
        page.getByLabel(eventDateAndTime).fill(eventData[5]);
        page.getByLabel(eventPrice).fill(eventData[6]);
        page.getByLabel(eventTotalSeats).fill(eventData[7]);
        page.getByLabel(eventImageURL).fill("https://www.seasiainfotech.com/uploads/blogs/QA_Automation_1.webp");

        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(addEventBtn))).isEnabled();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(addEventBtn)).click();

        assertThat(page.getByText("Event created!")).isVisible();

        page.waitForTimeout(3000);

    }

    public void createEvent(HashMap<String, String> data)   {

        page.navigate(event_URL);


        page.locator(eventTitle).fill(data.get("titlePrefix"));
        page.locator(eventDescription).fill(data.get("description"));

        //  Selecting dropdown Value
        page.getByLabel(eventCategory).selectOption(data.get("Category"));
        page.getByLabel(eventCity).fill(data.get("city"));
        page.getByLabel(eventVenue).fill(data.get("venue"));
        page.getByLabel(eventDateAndTime).fill(data.get("dateTime"));
        page.getByLabel(eventPrice).fill(data.get("price"));
        page.getByLabel(eventTotalSeats).fill(data.get("totalSeats"));
        page.getByLabel(eventImageURL).fill("https://www.seasiainfotech.com/uploads/blogs/QA_Automation_1.webp");

        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(addEventBtn))).isEnabled();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(addEventBtn)).click();

        assertThat(page.getByText("Event created!")).isVisible();

        page.waitForTimeout(3000);


    }



    public void checkCreatedEvent() {

        page.locator("#nav-events").click();


        Locator eventCards = page.locator("#event-card");
        Locator targetCards = eventCards.filter(new Locator.FilterOptions().setHasText(eventData[0]));

        assertThat(targetCards).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
    }



    public void deleteEvent()   {

        page.navigate(event_URL);

        Locator allEventList = page.locator("#event-table-row");
        Locator targetEvent = allEventList.filter(new Locator.FilterOptions().setHasText(eventData[0]));

        targetEvent.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Delete")).click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete event")).click();

        assertThat(page.getByText("Event deleted")).isVisible();

        page.waitForTimeout(3000);

    }


    public void deleteEvent(HashMap<String, String> data)   {

        page.navigate(event_URL);

        Locator allEventList = page.locator("#event-table-row");
        Locator targetEvent = allEventList.filter(new Locator.FilterOptions().setHasText(data.get("titlePrefix")));


        System.out.println("Total # of Events : " + targetEvent.count());

//        Locator nextItem = targetEvent.filter(new Locator.FilterOptions().setHasText("Festival"));

/*
        targetEvent.filter(new Locator.FilterOptions().setHasText(data.get("Category")))
                    .filter(new Locator.FilterOptions().setHas(page.getByRole()))
        targetEvent.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Delete")).click();
*/

//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete event")).click();

//        assertThat(page.getByText("Event deleted")).isVisible();

        page.waitForTimeout(3000);

    }




}
