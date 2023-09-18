package store.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import store.helpers.Filters;

import java.util.List;


@Slf4j
public class SearchProductsPage extends BasePage {

    @FindBy(css = ".facet__title")
    private List<WebElement> listOfFilters;

    @FindBy(css = ".row.facet-list")
    private WebElement productFilter;

    @FindBy(css = "div.facet-option__checkbox--rating-stars")
    private List<WebElement> listOfSubFilters;

    @FindBy(css = ".product-info")
    private List<WebElement> listOfProducts;

    @FindBy(css = ".product-overview__headline-wrapper")
    private WebElement productHeadline;

    public SearchProductsPage(WebDriver driver) {
        super(driver);
    }

    public SearchProductsPage selectFilter(String text) throws InterruptedException {
        Thread.sleep(4000);
        for(WebElement element : listOfFilters) {
            if (element.getText().equalsIgnoreCase(text)){
                scrollByElement(element);
                clickOnElement(element);
                getAllElements(listOfSubFilters);
                break;
            }
        }
        return this;
    }

    public void selectSubFilter(String text) {
        for(WebElement element : listOfSubFilters) {
            if (element.getText().contains(text)){
                clickOnElement(element);
                break;
            }
        }
    }

    public SearchProductsPage filterProducts(String... filters) throws InterruptedException {
        getElement(productFilter);

        for(int i=0; i<filters.length; i++){
            if ((!filters[i].equals("-")) && (!filters[i].equals(" ")) && (filters[i] != null)) {
                switch (i){
                    case 0:
                        selectFilter(Filters.HIGHLIGHTS.getFilter());
                        break;
                    case 1:
                        selectFilter(Filters.MARKE.getFilter());
                        break;
                    case 2:
                        selectFilter(Filters.PRODUKTART.getFilter());
                        break;
                    case 3:
                        selectFilter(Filters.GESCHENK.getFilter());
                        break;
                    case 4:
                        selectFilter(Filters.FURWHEN.getFilter());
                        break;
                }
                selectSubFilter(filters[i]);
            }
        }
        return new SearchProductsPage(driver);
    }

    public int getProductQuantity() throws InterruptedException {
        Thread.sleep(3000);
        String quantity = getElement(productHeadline).getText()
                .replace(" ", "")
                .replace("(", " ")
                .replace(")", "")
                .split(" ")[1].trim();
        return Integer.parseInt(quantity);
    }
}
