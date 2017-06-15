import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Zones {
    private WebDriver driver;

    private boolean isGreaterThen(String a, String b)
    {
        return a.compareTo(b) < 0;
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void zones(){
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> rowsA = driver.findElements(By.cssSelector("tr.row>td:nth-of-type(3)>a"));

        for(int i =0; i < rowsA.size(); i++){
            rowsA.get(i).click();

            List<WebElement> rowsZone = driver.findElements(By.cssSelector("td:nth-of-type(3)>select>option[selected]"));

            for (int j = 0; j < rowsZone.size()-1; j++) {
                boolean isGreater = isGreaterThen(rowsZone.get(j).getAttribute("textContent"), rowsZone.get(j+1).getAttribute("textContent"));
                assertTrue(isGreater);
            }
            driver.navigate().back();
            rowsA = driver.findElements(By.cssSelector("tr.row>td:nth-of-type(3)>a"));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
