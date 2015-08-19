import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldTest {
    public HelloWorld helloworld = new HelloWorld();
    @Test
    public void test() {
        helloworld.hello();
        assertEquals("Hello World!", helloworld.getStr());
    }
}
