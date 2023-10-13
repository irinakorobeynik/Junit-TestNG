import org.junit.jupiter.api.*;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    private static final Cart testCart = new Cart("testCart");
    private static final RealItem testRealItem = new RealItem();
    private static final VirtualItem testVirtualItem = new VirtualItem();
    private static final double totalBeforeAddingItem = testCart.getTotalPrice();

    @BeforeAll
    public static void initAll() {
        populateItem(testVirtualItem);
        populateItem(testRealItem);
    }

    @Test
    public void testAddingVirtualItem() {
        testCart.addVirtualItem(testVirtualItem);
        assertAll("Test Adding VirtualItem via Total",
                () -> assertTrue(testCart.getTotalPrice() != 0, "Seems like  Virtual Item is not added, Total equals to 0."),
                () -> assertNotEquals(totalBeforeAddingItem, testCart.getTotalPrice(), "Seems like  Virtual Item is not added, " +
                        "total values wasn't changed"),
                () -> assertEquals(testCart.getTotalPrice(), getTotalPricePerItem(testVirtualItem), "Total is calculated wrong")
        );
    }

    @Test
    public void testAddingRealItem() {
        testCart.addRealItem(testRealItem);
        assertAll("Test Adding RealItem via Total",
                () -> assertTrue(testCart.getTotalPrice() != 0, "Seems like  Real Item is not added, Total equals to 0."),
                () -> assertNotEquals(totalBeforeAddingItem, testCart.getTotalPrice(), "Seems like  Real Item is not added, " +
                        "total values wasn't changed"),
                () -> assertEquals(testCart.getTotalPrice(), getTotalPricePerItem(testRealItem) + getTotalPricePerItem(testVirtualItem),
                        "Total is calculated wrong")
        );
    }


}
