import static org.junit.Assert.*;
import org.junit.*;
public class MarkdownParseTest {

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testLinkAtBeginning() {
        String contents= "[link title](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testInfiniteLoop() { //failed and got stuck in infinite loop
        String contents = ")[";
        List<String> expect = new ArrayList<String>();
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testRegular() {
        String contents = "# Title [link1](https://something.com)[link2](some-thing.html)";

        List<String> expect = new ArrayList<String>();
        expect.add("https://something.com");
        expect.add("some-thing.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

}
