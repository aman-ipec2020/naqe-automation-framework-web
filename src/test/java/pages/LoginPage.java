package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    Page page;
    String base_URL;
    private static final String email_label = "Email";
    private static final String password_label = "Password";

    public LoginPage(Page page, String url) {
        this.page = page;
        this.base_URL = url;
    }

    public void loginToApplication()    {

        //  Step-1: Enter Email ID
        page.getByLabel(email_label).fill("amanpradhan99.ap@gmail.com");

        //  Step-2: Enter Password
        page.getByLabel(password_label).fill("White_Cloud@2026");

        //  Step-3: search and click "Sign In" button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
    }

}
