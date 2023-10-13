import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import shop.RealItem;
import static org.junit.jupiter.api.Assertions.*;

public class RealItemTests extends BaseTest {
    private static final RealItem testRealItem = new RealItem();;

    @BeforeAll
    public static void initAll() {
        populateItem(testRealItem);
    }

    @Test
    public void testRealItemName() {
        assertAll("RealItem Test",
                () -> assertNotNull(testRealItem.getName(), "Name of Test Real Item in Null"),
                () -> assertTrue(testRealItem.getName().trim().length() > 0, "Name of Test Real Item less than 0")
        );
    }
}
