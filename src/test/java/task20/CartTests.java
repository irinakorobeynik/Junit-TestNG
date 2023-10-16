package task20;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;



public class CartTests extends BaseTest {

    private static final Cart testCart = new Cart("testCart");
    private static final RealItem testRealItem = new RealItem();
    private static final VirtualItem testVirtualItem = new VirtualItem();
    private static final double totalBeforeAddingItem = testCart.getTotalPrice();

    @BeforeTest(groups = {"regression","smoke"})
    public void initAll() {
        populateItem(testVirtualItem);
        populateItem(testRealItem);
    }

    @Test(groups = {"regression", "smoke"})
    public void testAddingVirtualItem() {
        testCart.addVirtualItem(testVirtualItem);
        SoftAssert asert = new SoftAssert();
        asert.assertTrue(testCart.getTotalPrice() != 0, "Seems like  Virtual Item is not added, Total equals to 0.");
        asert.assertNotEquals(totalBeforeAddingItem, testCart.getTotalPrice(), "Seems like  Virtual Item is not added total values wasn't changed");
        asert.assertEquals(testCart.getTotalPrice(), getTotalPricePerItem(testVirtualItem) + getTotalPricePerItem(testRealItem), "Total is calculated wrong");
        asert.assertAll();

    }

    @Test(groups = {"regression", "smoke"})
    public void testAddingRealItem() {
        testCart.addRealItem(testRealItem);
        SoftAssert asert = new SoftAssert();
        asert.assertTrue(testCart.getTotalPrice() != 0, "Seems like  Virtual Item is not added, Total equals to 0.");
        asert.assertNotEquals(totalBeforeAddingItem, testCart.getTotalPrice(), "Seems like  Virtual Item is not added total values wasn't changed");
        asert.assertEquals(testCart.getTotalPrice(), getTotalPricePerItem(testRealItem), "Total is calculated wrong");
        asert.assertAll();

    }


}
