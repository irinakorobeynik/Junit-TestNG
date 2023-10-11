import org.junit.jupiter.api.BeforeEach;
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


    @BeforeEach
    public void init() {
        parser = new JsonParser();
        testCart = new Cart("testCart");
        testRealItem = new RealItem();
        populateItem(testRealItem);
        testCart.addRealItem(testRealItem);
    }

    @ParameterizedTest
    @ValueSource(strings = {"testCart", "asd", "none","jUnit", "alexander-cart"})
    public void testNoSuchFileException(String argument) {
        File file = new File("src/main/resources/" + argument + ".json");
        NoSuchFileException thrown = assertThrows(NoSuchFileException.class, () ->
                testCart = parser.readFromFile(file));
        assertTrue(thrown.getMessage().contains(String.format("File %s.json not found!", file)));
    }


}
