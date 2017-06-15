import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Stickers {
    private WebDriver driver;

    private boolean areElementsPresent(WebElement webElement, By locator) {
        return webElement.findElements(locator).size() == 1;
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/");
        List<WebElement> productElements = driver.findElements(By.cssSelector("li[class*= product]"));
        for (int i = 0; i<productElements.size(); i++){
            assertTrue(areElementsPresent(productElements.get(i),(By.cssSelector("[class*=sticker]"))));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
