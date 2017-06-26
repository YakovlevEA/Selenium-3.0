package PageObjects.tests;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AddDelProductTest extends TestBase {

    @Test
    public void test(){

        for (int i = 1; i < 4; i++){
        app.openMainPage();
        app.openProductPage();
        app.addProductToBasket();
        assertTrue(app.basketConten(i));
        }

        app.openBasketPage();
        app.removeAllproductFromBasket();
    }
}
