package task20;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.RealItem;

public class RealItemTests extends BaseTest {
    private static final RealItem testRealItem = new RealItem();


    @BeforeClass(groups = {"smoke"})
    public void initAll() {
        populateItem(testRealItem);
    }

    @Test(groups = {"smoke"})
    public void testRealItemName() {
        SoftAssert asert = new SoftAssert();
        asert.assertNotNull(testRealItem.getName(), "Name of Test Real Item in Null");
        asert.assertTrue(testRealItem.getName().trim().length() > 0, "Name of Test Real Item less than 0");

    }
}
