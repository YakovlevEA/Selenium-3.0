package PageObjects.app;

import PageObjects.pages.BasketPage;
import PageObjects.pages.MainPage;
import PageObjects.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;


    public Application(){
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }
    public void openMainPage(){
        mainPage.open();
    }

    public void openProductPage(){
        mainPage.openProduct();
    }

    public void addProductToBasket(){
        productPage.addToBasket();
    }

    public boolean basketConten(int i){
        return productPage.isBasketContain(i);
    }

    public void openBasketPage(){
        basketPage.openByCheckout();
    }

    public void removeAllproductFromBasket(){
        basketPage.removeAllProducts();
    }

    public void quit(){
        driver.quit();
    }
}
