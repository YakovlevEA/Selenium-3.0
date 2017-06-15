import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class CountriesSorting {
    private WebDriver driver;

    //Метод для сравнения строк: 'а' больше чем 'b'
    public boolean isGreaterThen(String a, String b)
    {
        return a.compareTo(b) < 0;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void countriesSorting(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> rows = table.findElements(By.cssSelector("td:nth-of-type(5)>a"));

        for (int i = 0; i<rows.size()-1;i++) {
         boolean isGreater = isGreaterThen(rows.get(i).getAttribute("textContent"),rows.get(i+1).getAttribute("textContent"));
            assertTrue(isGreater);
        }

        List<WebElement> rowsZone = table.findElements(By.cssSelector("tr.row>td:nth-of-type(6)"));

        for (int i = 0; i<rowsZone.size();i++){

            if (!rowsZone.get(i).getAttribute("textContent").equals("0")){
                rows.get(i).click();

                WebElement tableZone = driver.findElement(By.cssSelector("table.dataTable"));
                List<WebElement> rowsZoneCountry = driver.findElements(By.cssSelector("input[type=hidden][name*=name]"));

                for (int j = 0; j<rowsZoneCountry.size()-1;j++){
                   boolean isGreater = isGreaterThen(rowsZoneCountry.get(j).getAttribute("value"),
                                                        rowsZoneCountry.get(j+1).getAttribute("value"));
                    assertTrue(isGreater);
                }

                driver.navigate().back();

                table = driver.findElement(By.cssSelector("table.dataTable"));
                rows = table.findElements(By.cssSelector("td:nth-of-type(5)>a"));
                rowsZone = table.findElements(By.cssSelector("tr.row>td:nth-of-type(6)"));
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
