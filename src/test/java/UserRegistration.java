import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;


public class UserRegistration {
    WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void userRegistration(){
        String mail = "test" +System.currentTimeMillis() + "@test.com";

        driver.navigate().to("http://localhost/litecart/");

        WebElement newCustomers = driver.findElement(By.cssSelector("tr:nth-child(5) a"));
        newCustomers.click();

        WebElement taxId  = driver.findElement(By.cssSelector("[name=tax_id]"));
        taxId.sendKeys("1");

        WebElement company = driver.findElement(By.cssSelector("[name=company]"));
        company.sendKeys("testCompany");

        WebElement firstName = driver.findElement(By.cssSelector("[name=firstname]"));
        firstName.sendKeys("firstName");

        WebElement lastName = driver.findElement(By.cssSelector("[name=lastname]"));
        lastName.sendKeys("lastName");

        WebElement address1 = driver.findElement(By.cssSelector("[name=address1]"));
        address1.sendKeys("address1");

        WebElement address2 = driver.findElement(By.cssSelector("[name=address2]"));
        address2.sendKeys("address2");

        WebElement postcode = driver.findElement(By.cssSelector("[name=postcode]"));
        postcode.sendKeys("12345");

        WebElement city = driver.findElement(By.cssSelector("[name=city]"));
        city.sendKeys("city");

        WebElement country = driver.findElement(By.cssSelector("select[name=country_code]"));
        Select selectCountry = new Select(country);
        selectCountry.selectByValue("US");

        WebElement zoneCode = driver.findElement(By.cssSelector("select[name=zone_code]"));
        Select selectZoneCode = new Select(zoneCode);
        selectZoneCode.selectByValue("AK");

        WebElement email = driver.findElement(By.cssSelector("[name=email]"));
        email.sendKeys(mail);

        WebElement phone = driver.findElement(By.cssSelector("[name=phone]"));
        phone.sendKeys("123456789");

        WebElement password = driver.findElement(By.cssSelector("[name=password]"));
        password.sendKeys("password");

        WebElement confirmedPassword = driver.findElement(By.cssSelector("[name=confirmed_password]"));
        confirmedPassword.sendKeys("password");

        WebElement createAccount = driver.findElement(By.cssSelector("[name=create_account]"));
        createAccount.click();

        WebElement logout = driver.findElement(By.cssSelector("#box-account li:nth-child(4) a"));
        logout.click();

        WebElement emailLogin = driver.findElement(By.cssSelector("[name=email]"));
        emailLogin.sendKeys(mail);

        WebElement passwordLogin = driver.findElement(By.cssSelector("[name=password]"));
        passwordLogin.sendKeys("password");

        WebElement login = driver.findElement(By.cssSelector("[name=login]"));
        login.click();

        logout = driver.findElement(By.cssSelector("#box-account li:nth-child(4) a"));
        logout.click();

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
