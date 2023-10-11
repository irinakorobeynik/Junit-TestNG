import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualItemTests extends BaseTest {

    private VirtualItem testVirtualItem;
    private double originalSizeOnDisk;

    @BeforeEach
    public void init() {
        testVirtualItem = new VirtualItem();
        populateItem(testVirtualItem);
        originalSizeOnDisk = testVirtualItem.getSizeOnDisk();
    }

    @Test
    public void testSettingSizeOnDisk() {
        testVirtualItem.setSizeOnDisk(NEW_SIZE_ON_DISK);
        assertAll("SizeOnDisk Test",
                () -> assertNotEquals(testVirtualItem.getSizeOnDisk(), originalSizeOnDisk),
                () -> assertEquals(testVirtualItem.getSizeOnDisk(), NEW_SIZE_ON_DISK)
        );
    }
}
