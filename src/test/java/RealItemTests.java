import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import shop.RealItem;
import static org.junit.jupiter.api.Assertions.*;

public class RealItemTests extends BaseTest {
    private RealItem testRealItem;

    @BeforeEach
    public void init() {
        testRealItem = new RealItem();
        populateItem(testRealItem);
    }

    @Test
    public void testRealItemName() {
        assertAll("RealItem Test",
                () -> assertNotNull(testRealItem.getName()),
                () -> assertTrue(testRealItem.getName().trim().length() > 0)
        );
    }
}
