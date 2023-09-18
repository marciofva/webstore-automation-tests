import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import store.pages.HomePage;


@Execution(ExecutionMode.CONCURRENT)
public class TestListProducts extends BaseTest {

    @Tag("listProducts")
    @DisplayName("List Products by Filter")
    @ParameterizedTest
    @CsvFileSource(resources = "/filterData.csv", useHeadersInDisplayName = true, delimiterString = ",")
    public void shouldListProductsByFilter(String highlights,
                                           String brand,
                                           String productType,
                                           String giftFor, String forWhom,
                                           int totalProducts) throws InterruptedException {

        assertEquals(totalProducts, (new HomePage(driver).goToMenuOption("PARFUM").filterProducts(highlights, brand, productType, giftFor, forWhom).getProductQuantity()));
    }
}
