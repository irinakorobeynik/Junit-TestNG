package task20;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.VirtualItem;


public class VirtualItemTests extends BaseTest {

    private static final VirtualItem testVirtualItem = new VirtualItem();
    ;
    private static double originalSizeOnDisk = 0;

    @BeforeTest(groups = {"regression","smoke"})
    public void init() {
        populateItem(testVirtualItem);
        originalSizeOnDisk = testVirtualItem.getSizeOnDisk();
    }

    @Test(groups = {"regression", "smoke"})
    public void testSettingSizeOnDisk() {
        testVirtualItem.setSizeOnDisk(NEW_SIZE_ON_DISK);
        SoftAssert asert = new SoftAssert();
        asert.assertNotEquals(testVirtualItem.getSizeOnDisk(), originalSizeOnDisk, "Size on Disk is not updated from previous size.");
        asert.assertEquals(testVirtualItem.getSizeOnDisk(), NEW_SIZE_ON_DISK, "New size on Disk is not applied.");

    }
}
