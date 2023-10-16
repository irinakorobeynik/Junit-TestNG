package task20;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import java.io.File;


public class ParserTests extends BaseTest {
    private static final Parser parser = new JsonParser();
    private static final RealItem testRealItem = new RealItem();
    private static Cart testCart;
    private static File file;

    @BeforeTest(groups = {"regression","smoke"})
    public void init() {
        testCart = new Cart(getRandomString());
        populateItem(testRealItem);
        testCart.addRealItem(testRealItem);
    }



    @Test(dataProvider = "fileDataProvider", dataProviderClass = BaseTest.class, expectedExceptions = NoSuchFileException.class,
            expectedExceptionsMessageRegExp = "(?s).*json not found!.*", groups = {"regression"})
    public void testNoSuchFileException(String fileName) {
        file = new File(getPath(fileName));
        testCart = parser.readFromFile(file);
    }


    @Test(enabled = false, groups = {"regression", "Smoke"})
    public void testWritingAndReadingJson() {
        parser.writeToFile(testCart);
        file = new File(getPath(testCart.getCartName()));
        SoftAssert asert = new SoftAssert();
        asert.assertTrue(file.exists(), "File doesn't exists");
        asert.assertTrue(file.length() > 0, "File has no content");
        Cart newTestCart = parser.readFromFile(file);
        asert.assertEquals(newTestCart.getCartName(), testCart.getCartName(), "Cart name doesn't match");
        asert.assertEquals(newTestCart.getTotalPrice(), getTotalPricePerItem(testRealItem), "Total is different for two Carts");
    }

    @AfterTest
    public void deleteFile() {
        file.deleteOnExit();
    }


}


