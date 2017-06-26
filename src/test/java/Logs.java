import org.openqa.selenium.*;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Logs {
    private WebDriver driver;

    public void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get("browser");
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

    @Before
    public void start(){
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(caps);
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void logs(){

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElements(By.cssSelector("#app->a")).get(1).click();
        driver.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(3) a")).click();

        List<WebElement> refToProduct = driver.findElements(By.cssSelector((".dataTable td:nth-of-type(3)>a")));

        for (int i = 1; i< refToProduct.size(); i++){

            refToProduct.get(i).click();
            driver.navigate().back();
            refToProduct = driver.findElements(By.cssSelector((".dataTable td:nth-of-type(3)>a")));
        }

       /* 1.
       List<LogEntry> logs  = driver.manage().logs().get("browser").getAll();
        for (LogEntry logEntry: logs) {
            System.out.println(logEntry);}*/

        /* 2.
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
        }*/

        // 3.
        analyzeLog();

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
