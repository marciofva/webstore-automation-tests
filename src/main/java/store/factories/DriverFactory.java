package store.factories;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import store.choices.Browser;
import store.choices.Host;

import java.net.MalformedURLException;
import java.net.URL;


@Slf4j
public class DriverFactory {
    private static final Browser BROWSER = Browser.valueOf(getConfig().getString("browser").toUpperCase());

    public static Config getConfig(){
        return ConfigFactory.load("config.properties");
    }

    public static WebDriver getDriver() {
        log.info("Getting driver for host: {}", getConfig().getString("host"));
        if(getConfig().getString("host").equals(Host.LOCALHOST.getHost())){
            return getLocalWebDriver();
        }else{
            if(getConfig().getString("host").equals(Host.DOCKER_SELENIUM_GRID.getHost())){
                return getRemoteWebDriver();
            } else{
                throw new IllegalStateException(String.format("%s is not a valid HOST. Pick your HOST from %s.", getConfig().getString("host"), java.util.Arrays.asList(Host.values())));
            }
        }
    }

    private static WebDriver getLocalWebDriver() {
        log.info("Getting driver for browser: {}", BROWSER);
        switch (BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(CapabilitiesFactory.getChromeOptions());
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(CapabilitiesFactory.getFirefoxOptions());
            case EDGE:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(CapabilitiesFactory.getEdgeOptions());
            default:
                throw new IllegalStateException(String.format("%s is not a valid browser. Pick your browser from %s.", BROWSER, java.util.Arrays.asList(BROWSER.values())));
        }
    }

    private static WebDriver getRemoteWebDriver() {
        String HOST_URI = ConfigFactory.load("capabilities").getString("HOST_URI");

        switch (BROWSER) {
            case CHROME:
            case FIREFOX:
            case EDGE:
                return new RemoteWebDriver(getHostURL(HOST_URI), CapabilitiesFactory.getCapabilities(BROWSER));
            default:
                throw new IllegalStateException(String.format("%s is not a valid browser. Pick your browser from %s.", BROWSER, java.util.Arrays.asList(BROWSER.values())));
        }
    }

    public static URL getHostURL(String hostUri) {
        log.info("Getting hostURL for Host: {}", hostUri);
        try {
            return new URL(hostUri);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(String.format("%s is Malformed host URL.", hostUri), e);
        }
    }
}
