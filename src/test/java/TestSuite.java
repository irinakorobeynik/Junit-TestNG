import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({CartTests.class, ParserTests.class, RealItemTests.class, VirtualItemTests.class})

public class TestSuite {
}
