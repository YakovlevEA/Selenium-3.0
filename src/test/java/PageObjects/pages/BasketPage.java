package PageObjects.pages;

import PageObjects.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openByCheckout(){
    WebElement checkout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(., 'Checkout')]")));
    checkout.click();
    }
    public void removeAllProducts() {
        List<WebElement> countRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td.item")));
        for (int i = 1; i <= countRows.size(); i++) {
            WebElement paymentDue = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr.footer td:nth-child(2) strong")));
            WebElement remove = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=remove_cart_item]")));
            remove.click();
            if (i != countRows.size()) {
                wait.until(ExpectedConditions.stalenessOf(paymentDue));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr.footer td:nth-child(2) strong")));
            }
        }
    }
}
