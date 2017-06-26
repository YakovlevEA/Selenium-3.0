package PageObjects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open(){
        driver.navigate().to("http://localhost/litecart/");
        return this;
    }

    @FindBy(css = ".content a.link")
    private WebElement firstProduct;

    public void openProduct(){
        firstProduct.click();
    }


}
