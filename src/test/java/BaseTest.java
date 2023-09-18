import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import store.factories.DriverFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


public abstract class BaseTest {
    protected WebDriver driver = DriverFactory.getDriver();
    public static final String TITLE = "Online-Parfümerie ✔️ Parfum & Kosmetik kaufen | DOUGLAS";

    @BeforeEach
    public void setUp() {
        driver.get(DriverFactory.getConfig().getString("url"));
        assertEquals(TITLE, driver.getTitle());
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
