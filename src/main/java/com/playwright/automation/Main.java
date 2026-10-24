package com.playwright.automation;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create(); //start playwright server

        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
        lp.setChannel("Chrome").setHeadless(false);

        Browser browser = playwright.chromium().launch(lp); // which browser to launch
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");

        String title = page.title();
        System.out.println("Page Title : " + title);

        browser.close();
        playwright.close();
    }
}