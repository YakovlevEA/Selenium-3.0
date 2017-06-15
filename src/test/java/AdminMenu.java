import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class AdminMenu {

    private WebDriver driver;

    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void myFirstTest() {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> sizeM = driver.findElements(By.cssSelector("#app->a"));
        for (int i = 0; i<sizeM.size(); i++){
            List<WebElement> elementsM = driver.findElements(By.cssSelector("#app->a"));
            elementsM.get(i).click();
            assertTrue(areElementsPresent(driver,(By.cssSelector("h1"))));

            List<WebElement> sizeSubM = driver.findElements(By.cssSelector("[id^=doc]>a"));
            for (int j = 0; j<sizeSubM.size(); j++){
                List<WebElement> elementsSubM = driver.findElements(By.cssSelector("[id^=doc]>a"));
                elementsSubM.get(j).click();
                assertTrue(areElementsPresent(driver, By.cssSelector("h1")));
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
