package PageObjects.pages;

import PageObjects.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    private boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void addToBasket() {
        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(("[name=add_cart_product]"))));
        boolean isSelectSize = isElementPresent(driver, By.cssSelector("select"));
        if (isSelectSize) {
            Select selectSize = new Select(driver.findElement(By.cssSelector("select")));
            selectSize.selectByValue("Medium");
        }
        addToCart.click();
    }

    public boolean isBasketContain(int i){
      boolean rez = wait.until(ExpectedConditions.attributeContains(By.cssSelector("span.quantity"), "textContent", String.valueOf(i)));
        return rez;
    }
}
