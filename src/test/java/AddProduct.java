import org.junit.*;
import org.openqa.selenium.*;
import  org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class AddProduct {
    WebDriver driver;

    private boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }

    @Test
    public void addProduct(){
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // General
        WebElement catalog = driver.findElement(By.cssSelector("li#app-:nth-of-type(2)>a"));
        catalog.click();

        WebElement addNewProduct = driver.findElement(By.cssSelector("#content a:nth-of-type(2)"));
        addNewProduct.click();

        WebElement statusEnabled = driver.findElement(By.cssSelector("label:nth-of-type(1)"));
        statusEnabled.click();

        WebElement name = driver.findElement(By.cssSelector("[name^=name]"));
        name.sendKeys("ROBOFISH");

        WebElement code = driver.findElement(By.cssSelector("[name=code]"));
        code.sendKeys("123456");

        WebElement productGroup = driver.findElement(By.cssSelector("tr:nth-of-type(4) input[name^=product_groups]"));
        productGroup.click();

        WebElement quantity = driver.findElement(By.cssSelector("[name=quantity]"));
        quantity.sendKeys("12");

        WebElement image = driver.findElement(By.cssSelector("[name^=new_images]"));
        image.sendKeys(new File("src/img/ROBOFISH.png").getAbsolutePath());

        WebElement dateFrom = driver.findElement(By.cssSelector("[name=date_valid_from]"));
        dateFrom.sendKeys("18062017");

        WebElement dateTo = driver.findElement(By.cssSelector("[name=date_valid_to]"));
        dateTo.sendKeys("18072017");

        //Information

        WebElement informationTab = driver.findElement(By.cssSelector(".index li:nth-of-type(2) a"));
        informationTab.click();

        WebElement manufacturer = driver.findElement(By.cssSelector("[name=manufacturer_id]"));
        Select selectManufacturer = new Select(manufacturer);
        selectManufacturer.selectByValue("1");

        WebElement keywords = driver.findElement(By.cssSelector("[name=keywords]"));
        keywords.sendKeys("water toys");

        WebElement shortDescription = driver.findElement(By.cssSelector("[name^=short_description]"));
        shortDescription.sendKeys("Fish ROBO FISH Clownfish (green)");

        WebElement description = driver.findElement(By.cssSelector(".trumbowyg-editor"));
        description.sendKeys("Rabaraba Clown - innovative high-tech toy. Rabaraba ROBO FISH activates in water. It very accurately mimics the movement and habits of this fish. Electromagnetic motor allows the fish to move in 5 directions - up, down, forward, right and left. When diving in an aquarium or other container with water, Rabaraba begins to swim, sinking to the bottom and rising to the surface of the water.\n" +
                "The toy is powered by two A76 alkaline batteries or RL44, which are included in the kit.");

        WebElement headTitle = driver.findElement(By.cssSelector("[name^=head_title]"));
        headTitle.sendKeys("ROBOFISH ELECTRONIC RABARABA CLOWN GREEN");

        WebElement metaDescription = driver.findElement(By.cssSelector("[name^=meta_description]"));
        metaDescription.sendKeys("ROBOFISH ELECTRONIC");

        //Prices
        WebElement pricesTab = driver.findElement(By.cssSelector(".index li:nth-of-type(4) a"));
        pricesTab .click();

        WebElement purchasePrice = driver.findElement(By.cssSelector("[name=purchase_price]"));
        purchasePrice.sendKeys("1");

        WebElement purchasePriceCurrencyCode = driver.findElement(By.cssSelector("[name=purchase_price_currency_code]"));
        Select selectPurchasePriceCurrencyCode = new Select(purchasePriceCurrencyCode);
        selectPurchasePriceCurrencyCode.selectByValue("USD");

        WebElement pricesUSD = driver.findElement(By.cssSelector("[name=prices\\[USD\\]]"));
        pricesUSD.sendKeys("2");

        WebElement pricesEUR = driver.findElement(By.cssSelector("[name=prices\\[EUR\\]]"));
        pricesEUR.sendKeys("1.8");


        WebElement save = driver.findElement(By.cssSelector("[name=save]"));
        save.click();

        assertTrue(areElementsPresent(driver, By.xpath("//a[contains(., 'ROBOFISH')]")));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
