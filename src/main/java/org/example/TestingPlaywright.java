package org.example;

import com.microsoft.playwright.*;

public class TestingPlaywright {

    public static void main(String[] args) {
        // 1) Start Playwright engine (core library that can drive browsers)
        Playwright playwright = Playwright.create();

        // 2) Launch a Browser (like new ChromeDriver in Selenium)
        Browser browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));

        // 3) Create a BrowserContext (isolated profile, like an Incognito window)
        BrowserContext context = browser.newContext();

        // 4) Create a Page (a tab inside that context → where tests run)
        Page page = context.newPage();
//
//        // 5) Navigate somewhere
//        page.navigate("https://playwright.dev");
//
//        // 6) Interact with the page (grab title, click, etc.)
//        System.out.println("Page title is: " + page.title());

        // 7) Cleanup in reverse order
//        context.close();   // closes the incognito profile + its tabs
//        browser.close();   // closes the browser instance
//        playwright.close();// shuts down Playwright engine

    }
}
