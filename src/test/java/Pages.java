import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Pages {
    WebDriver driver;

    @Before
    public void start(){
         driver = new ChromeDriver();
       //driver = new FirefoxDriver();
       //driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void pages(){
        driver.navigate().to("http://localhost/litecart/");

        WebElement nameMain  = driver.findElement(By.cssSelector("div#box-campaigns div.name"));
        WebElement priceMain = driver.findElement(By.cssSelector("div#box-campaigns s"));
        WebElement salePriceMain = driver.findElement(By.cssSelector("div#box-campaigns strong"));

        String nameM = nameMain.getText();
        String priceM = priceMain.getText();
        String salePriceM = salePriceMain.getText();

        String[] colorM = priceMain.getCssValue("color").split("(\\()|(, )|(\\))");;
        String[] colorSaleM = salePriceMain.getCssValue("color").split("(\\()|(, )|(\\))");

        int fontSizeM = Integer.parseInt(priceMain.getAttribute("offsetHeight"));
        int fontSizeSaleM = Integer.parseInt(salePriceMain.getAttribute("offsetHeight"));

        WebElement ref = driver.findElement(By.cssSelector("div#box-campaigns .link"));
        ref.click();

        WebElement nameProduct  = driver.findElement(By.cssSelector("h1.title"));
        WebElement priceProduct = driver.findElement(By.cssSelector("div.information s"));
        WebElement salePriceProduct = driver.findElement(By.cssSelector("div.information strong"));

        String nameP = nameProduct.getText();
        String priceP = priceProduct.getText();
        String salePriceP = salePriceProduct.getText();

        String[] colorP = priceProduct.getCssValue("color").split("(\\()|(, )|(\\))");
        String[] colorSaleP = salePriceProduct.getCssValue("color").split("(\\()|(, )|(\\))");

        int fontSizeP = Integer.parseInt(priceProduct.getAttribute("offsetHeight"));
        int fontSizeSaleP = Integer.parseInt(salePriceProduct.getAttribute("offsetHeight"));

        assertTrue(nameM.equals(nameP));
        assertTrue(priceM.equals(priceP));
        assertTrue(salePriceM.equals(salePriceP));
        assertTrue(fontSizeM<fontSizeSaleM);
        assertTrue(fontSizeP<fontSizeSaleP);

        assertTrue(colorM[1].equals(colorM[2]) & colorM[2].equals(colorM[3]));
        assertTrue(colorSaleM[2].equals("0") & colorSaleM[3].equals("0") & !colorSaleM[1].equals("0"));

        assertTrue(colorP[1].equals(colorP[2]) & colorP[2].equals(colorP[3]));
        assertTrue(colorSaleP[2].equals("0") & colorSaleP[3].equals("0") & !colorSaleP[1].equals("0"));

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
