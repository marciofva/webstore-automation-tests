package store.factories;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import store.choices.Browser;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;


public class CapabilitiesFactory {
    private static Config config = ConfigFactory.load("capabilities");

    private static final boolean HEADLESS = Boolean.parseBoolean(DriverFactory.getConfig().getString("headless"));
    private static final boolean ACCEPT_INSECURE_CERTIFICATES = Boolean.parseBoolean(config.getString("ACCEPT_INSECURE_CERTIFICATES"));
    private static final String SELENIUM_LOG_LEVEL = config.getString("SELENIUM_LOG_LEVEL");
    private static final String DOWNLOADS_DIR = config.getString("DOWNLOADS_DIR");

    public static Capabilities getCapabilities(Browser browser) {
        switch (browser) {
            case CHROME:
                return getChromeOptions();
            case FIREFOX:
                return getFirefoxOptions();
            case EDGE:
                return getEdgeOptions();
            default:
                throw new IllegalStateException(String.format("%s is not a valid browser choice. Pick your browser from %s.", browser, java.util.Arrays.asList(browser.values())));
        }
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(ACCEPT_INSECURE_CERTIFICATES);
        chromeOptions.setHeadless(HEADLESS);
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--enable-javascript");
        chromeOptions.addArguments("--disable-notifications");

        Map<String, Object> prefs = new Hashtable<String, Object>();
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("download.default_directory", String.format("%s\\%s", System.getProperty("user.dir"), DOWNLOADS_DIR));
        prefs.put("profile.default_content_settings.cookies", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);

        // To get error console logs
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.parse(SELENIUM_LOG_LEVEL));
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(ACCEPT_INSECURE_CERTIFICATES);
        firefoxOptions.setHeadless(HEADLESS);
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");
        return firefoxOptions;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("disable-gpu");
        edgeOptions.setHeadless(HEADLESS);
        return edgeOptions;
    }
}
