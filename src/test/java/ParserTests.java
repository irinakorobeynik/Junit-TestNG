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
    private Parser parser;
    private Cart testCart;
    private RealItem testRealItem;
    private static File file;

    @BeforeEach
    public void init() {
        parser = new JsonParser();
        testCart = new Cart(getRandomString());
        testRealItem = new RealItem();
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
        assertTrue(thrown.getMessage().contains(String.format("File %s.json not found!", file)));
    }


    @Test
    public void testWritingAndReadingJson() {
        parser.writeToFile(testCart);
        file = new File(getPath(testCart.getCartName()));
        assertAll("Writing/Reading file test",
                () -> {
                    assertAll("Writing test",
                            () -> assertTrue(file.exists()),
                            () -> assertTrue(file.length() > 0)
                    );
                },
                () -> {
                    Cart newTestCart = parser.readFromFile(file);
                    assertAll("Reading test",
                            () -> assertEquals(newTestCart.getCartName(), testCart.getCartName()),
                            () -> assertEquals(newTestCart.getTotalPrice(), getTotalPricePerItem(testRealItem))
                    );
                }
        );
    }

    @AfterEach
    public void deleteFile() {
        file.deleteOnExit();
    }

}


