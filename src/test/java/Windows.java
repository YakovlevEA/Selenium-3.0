import org.openqa.selenium.*;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Windows {
    WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void windows() {
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("a.button")).click();

        List<WebElement> refToWindow = driver.findElements(By.cssSelector("i.fa.fa-external-link"));

        String mainWindow = driver.getWindowHandle();
        final Set<String> oldWindows = driver.getWindowHandles();

        for (int i = 0; i < refToWindow.size(); i++) {
            refToWindow.get(i).click();

            String newWindow = wait.until(new ExpectedCondition<String>() {
                public String apply(WebDriver driver) {
                    Set<String> newWindows = driver.getWindowHandles();
                    newWindows.removeAll(oldWindows);
                    return newWindows.size() > 0 ?
                            newWindows.iterator().next() : null;
                }
            });
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
