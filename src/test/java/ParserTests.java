import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTests extends BaseTest {
    private static final Parser parser= new JsonParser();
    private static final RealItem testRealItem = new RealItem();
    private static Cart testCart;
    private static File file;

    @BeforeEach
    public void init() {
        testCart = new Cart(getRandomString());
        populateItem(testRealItem);
        testCart.addRealItem(testRealItem);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {NON_EXISTING_FILE_NAME_4, NON_EXISTING_FILE_NAME_2, NON_EXISTING_FILE_NAME_3, NON_EXISTING_FILE_NAME_5,
            NON_EXISTING_FILE_NAME_1})
    public void testNoSuchFileException(String argument) {
        file = new File(getPath(argument));
        NoSuchFileException thrown = assertThrows(NoSuchFileException.class, () ->
                testCart = parser.readFromFile(file));
        assertTrue(thrown.getMessage().contains(String.format("File %s.json not found!", file)), "Exception doesn't contain such text");
    }


    @Test
    public void testWritingAndReadingJson() {
        parser.writeToFile(testCart);
        file = new File(getPath(testCart.getCartName()));
        assertAll("Writing/Reading file test",
                () -> {
                    assertAll("Writing test",
                            () -> assertTrue(file.exists(), "File doesn't exists"),
                            () -> assertTrue(file.length() > 0, "File has no content")
                    );
                },
                () -> {
                    Cart newTestCart = parser.readFromFile(file);
                    assertAll("Reading test",
                            () -> assertEquals(newTestCart.getCartName(), testCart.getCartName(), "Cart name doesn't match"),
                            () -> assertEquals(newTestCart.getTotalPrice(), getTotalPricePerItem(testRealItem), "Total is different for two Carts")
                    );
                }
        );
    }

    @AfterEach
    public void deleteFile() {
        file.deleteOnExit();
    }

}


