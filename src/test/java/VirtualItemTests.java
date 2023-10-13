import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualItemTests extends BaseTest {

    private static final VirtualItem testVirtualItem =  new VirtualItem();;
    private static double originalSizeOnDisk = 0;

    @BeforeAll
    public static void init() {
        populateItem(testVirtualItem);
        originalSizeOnDisk = testVirtualItem.getSizeOnDisk();
    }

    @Test
    public void testSettingSizeOnDisk() {
        testVirtualItem.setSizeOnDisk(NEW_SIZE_ON_DISK);
        assertAll("SizeOnDisk Test",
                () -> assertNotEquals(testVirtualItem.getSizeOnDisk(), originalSizeOnDisk, "Size on Disk is not updated from previous size."),
                () -> assertEquals(testVirtualItem.getSizeOnDisk(), NEW_SIZE_ON_DISK, "New size on Disk is not applied.")
        );
    }
}
