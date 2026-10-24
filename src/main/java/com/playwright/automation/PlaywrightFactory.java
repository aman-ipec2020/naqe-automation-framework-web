package com.playwright.automation;

import com.microsoft.playwright.*;

public final class PlaywrightFactory {

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    private PlaywrightFactory() {}

    // Starting the Browser and creating the Playwright Objects
    public static void launchBrowser() {

        String browserName = System.getProperty("browser", "chromium")
                                    .toLowerCase();

        boolean headless = Boolean.parseBoolean(
                                    System.getProperty("headless", "false"));

        Playwright pw = Playwright.create();
        playwright.set(pw);

        Browser launchedBrowser;

        switch (browserName)
        {
            case "chromium" ->
                    launchedBrowser = pw.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(headless));

            case "firefox" ->
                    launchedBrowser = pw.firefox().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(headless));

            case "webkit" ->
                    launchedBrowser = pw.webkit().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(headless));

            default -> {
                pw.close();
                playwright.remove();
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName);
            }
        }

        browser.set(launchedBrowser);

        BrowserContext browserContext =
                launchedBrowser.newContext();

        context.set(browserContext);
        page.set(browserContext.newPage());
    }

    public static Page getPage() {
        Page currentPage = page.get();

        if (currentPage == null) {
            throw new IllegalStateException(
                    "Browser page is not initialized");
        }

        return currentPage;
    }

    // Closing the Browser and releasing the Playwright Objects
    public static void shutdownBrowser()
    {
        try {
            if (context.get() != null) {
                context.get().close();
            }
        } finally {
            try {
                if (browser.get() != null) {
                    browser.get().close();
                }
            } finally {
                try {
                    if (playwright.get() != null) {
                        playwright.get().close();
                    }
                } finally {
                    page.remove();
                    context.remove();
                    browser.remove();
                    playwright.remove();
                }
            }
        }
    }
}
