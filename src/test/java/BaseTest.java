import shop.Item;
import org.apache.commons.lang3.RandomStringUtils;
import shop.RealItem;
import shop.VirtualItem;

public abstract class  BaseTest {
    public static final double TAX = 0.2;
    public static final double MIN = 1.00;
    public static final double MAX = 100.0;
    public static final double NEW_SIZE_ON_DISK = 0.00;
    public static final String  NON_EXISTING_FILE_NAME_1 = "testCart";
    public static final String  NON_EXISTING_FILE_NAME_2 ="asd";
    public static final String  NON_EXISTING_FILE_NAME_3 ="jUnit";
    public static final String  NON_EXISTING_FILE_NAME_4 ="alexander-cart";
    public static final String  NON_EXISTING_FILE_NAME_5 ="none";

    public double getTotalPricePerItem (Item item){
        return item.getPrice()+item.getPrice()*TAX;
    }

    public static double getRandomDouble(){
        return (Math.random() * (MAX - MIN)) + MIN;
    }

    public static String getRandomString(){
        return RandomStringUtils.randomAlphanumeric((int) getRandomDouble());

    }

    public static void populateItem(VirtualItem item){
        item.setName(getRandomString());
        item.setPrice(getRandomDouble());
        item.setSizeOnDisk(getRandomDouble());
    }

    public static void populateItem(RealItem item){
        item.setName(getRandomString());
        item.setPrice(getRandomDouble());
        item.setWeight(getRandomDouble());
    }

    public String getPath(String fileName){
        return "src/main/resources/" + fileName + ".json";
    }

}
