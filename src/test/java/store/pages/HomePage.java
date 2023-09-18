package store.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends BasePage {

    @FindBy(css = "button.button.button__primary.uc-list-button__accept-all")
    private WebElement cookieConsentBtn;

    @FindBy(css = "a[type='nav-heading']")
    private List<WebElement> menuOptions;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookieConsent() {
        getElement(cookieConsentBtn).click();
    }

    public void selectMenuOption(String option) {
        for(WebElement element : menuOptions) {
            if (element.getText().equalsIgnoreCase(option)){
                clickOnElement(element);
                break;
            }
        }
    }

    public SearchProductsPage goToMenuOption(String option) {
        acceptCookieConsent();
        selectMenuOption(option);
        return new SearchProductsPage(driver);
    }
}
