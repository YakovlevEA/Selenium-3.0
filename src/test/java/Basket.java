import org.openqa.selenium.*;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Basket {
    WebDriver driver;
    boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @Before
    public void start(){
        driver = new ChromeDriver();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void basket(){
        driver.navigate().to("http://localhost/litecart/");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        for (int i = 1; i<4; i++) {
            WebElement productLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".content a.link")));
            productLink.click();

            WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(("[name=add_cart_product]"))));

            // Т.к. предыдущий элемент "[name=add_cart_product]" появился ,
            // то элемент "select" также уже должен был появиться или не появиться
            boolean isSelectSize = isElementPresent(driver, By.cssSelector("select"));

            if (isSelectSize) {
                Select selectSize = new Select(driver.findElement(By.cssSelector("select")));
                selectSize.selectByValue("Medium");
            }

            addToCart.click();

            wait.until(ExpectedConditions.attributeContains(By.cssSelector("span.quantity"), "textContent", String.valueOf(i)));

            driver.navigate().back();
        }


        WebElement checkout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(., 'Checkout')]")));
        checkout.click();

        List<WebElement> countRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td.item")));

        for (int i = 1; i <= countRows.size(); i++){

        WebElement paymentDue = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr.footer td:nth-child(2) strong")));

        WebElement remove = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=remove_cart_item]")));
        remove.click();

            if(i != countRows.size()) {
            wait.until(ExpectedConditions.stalenessOf(paymentDue));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr.footer td:nth-child(2) strong")));
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
