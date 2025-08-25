package Utils;

import com.microsoft.playwright.*;

public class PlaywrightManager {
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public static void start() {
        Playwright pw = Playwright.create();
        playwright.set(pw);

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
        context.set(br.newContext());
        page.set(context.get().newPage());
    }

    public static Page page() { return page.get(); }
    public static BrowserContext context() { return context.get(); }

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