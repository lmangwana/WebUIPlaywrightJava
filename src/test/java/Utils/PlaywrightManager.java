package Utils;

import com.microsoft.playwright.*;

/**
        Selenium vs Playwright (Java)
        	•	Selenium: you drive a WebDriver (e.g., ChromeDriver) which owns a single browser profile/session.
        	Tabs are WebElement interactions inside that session.

            •	Playwright: you compose Playwright → Browser → BrowserContext → Page.
            •	Playwright: the engine process that can launch browsers.
            •	Browser: an actual browser instance (Chromium/Firefox/WebKit).
            •	BrowserContext: isolated browser profile (own cookies/storage). Think “new incognito window”.
            This is the biggest practical difference vs Selenium and is why Playwright makes test isolation easy.
            •	Page: a tab within a context (what you interact with: locator(), click(), fill()).
         Why ThreadLocal?
         - Parallel tests run on different threads; each needs its own Page/Context to avoid data leakage.
         - ThreadLocal<T> gives each thread its own independent instance.
 */



public class PlaywrightManager {
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    /**
     * Start the stack for the CURRENT THREAD:
     * 1) Create Playwright engine
     * 2) Launch the right browser in headless/headed mode (from config.properties)
     * 3) Create a fresh Context (isolated storage/cookies)
     * 4) Create a new Page (tab) for the scenario to use

     * Selenium analogy:
     * - Selenium: new ChromeDriver() → single profile/session
     * - Playwright: newContext() gives you per-test isolation automatically.
     */
    public static void start() {
        // 1) Playwright engine

        Playwright pw = Playwright.create();
        playwright.set(pw);

        // 2) Launch browser with config-driven headless/headed
        BrowserType.LaunchOptions opts = new BrowserType.LaunchOptions().setHeadless(!ConfigManager.headed());
        String b = ConfigManager.browser().toLowerCase();
        Browser br;
        switch (b) {
            case "firefox" -> br = pw.firefox().launch(opts);
            case "webkit" -> br = pw.webkit().launch(opts);
            case "chrome" -> br = pw.chromium().launch(opts.setChannel("chrome"));
            default -> br = pw.chromium().launch(opts);
        }
        browser.set(br);

        // 3) Create an isolated profile (Context) WITH video recording
        Browser.NewContextOptions ctxOpts = new Browser.NewContextOptions()
                .setRecordVideoDir(java.nio.file.Paths.get("target/videos"))
                .setRecordVideoSize(1920, 1080); // optional, makes videos "full HD"

        BrowserContext ctx = br.newContext(ctxOpts);
        context.set(ctx);

        // 4) Create a new tab (Page) to interact with
        page.set(context.get().newPage());
    }

    /** Get the Page bound to the current thread (used inside steps/pages). */
    public static Page page() { return page.get(); }

    /** If you need cookies/storage isolation control or tracing, access the Context. */
    public static BrowserContext context() { return context.get(); }

    /**
     * Clean up in reverse order. Try/ignore pattern keeps tearDown robust even if one close fails.
     * We also call remove() on ThreadLocals to prevent memory leaks in long-lived runners.
     * 	•	close() → releases Playwright’s resources (real browser stuff).
     * 	•	remove() → clears the ThreadLocal pointer (Java memory housekeeping).
     */
    public static void stop() {
        try { if (context.get() != null) context.get().close(); } catch (Exception ignored) {}
        try { if (browser.get() != null) browser.get().close(); } catch (Exception ignored) {}
        try { if (playwright.get() != null) playwright.get().close(); } catch (Exception ignored) {}
        page.remove();
        context.remove();
        browser.remove();
        playwright.remove();
    }
}