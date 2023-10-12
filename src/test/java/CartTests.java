import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest{
    private Cart testCart;
    private RealItem testRealItem;
    private VirtualItem testVirtualItem;
    private double totalBeforeAddingItem;

    @BeforeEach
    public void init() {
        testCart = new Cart("testCart");
        testRealItem = new RealItem();
        testVirtualItem = new VirtualItem();
        populateItem(testRealItem);
        populateItem(testVirtualItem);
        totalBeforeAddingItem= testCart.getTotalPrice();

    }
    @Test
    public void testAddingRealItem() {
        testCart.addRealItem(testRealItem);
        assertAll("Test Adding RealItem via Total",
                () -> assertTrue(testCart.getTotalPrice() !=0),
                () -> assertNotEquals(totalBeforeAddingItem, testCart.getTotalPrice()),
                () -> assertEquals(testCart.getTotalPrice(), getTotalPricePerItem(testRealItem))
        );
    }

    @Test
    public void testAddingVirtualItem() {
        testCart.addVirtualItem(testVirtualItem);
        assertAll("Test Adding VirtualItem via Total",
                () -> assertTrue(testCart.getTotalPrice() !=0),
                () -> assertNotEquals(totalBeforeAddingItem, testCart.getTotalPrice()),
                () -> assertEquals(testCart.getTotalPrice(), getTotalPricePerItem(testVirtualItem))
        );
    }
}
