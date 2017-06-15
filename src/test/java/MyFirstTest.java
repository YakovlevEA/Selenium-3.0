import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MyFirstTest {

    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://software-testing.ru/");
        driver.findElement(By.name("q")).sendKeys("webdriver"+Keys.ENTER);
    }

   @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}