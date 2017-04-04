import java.io.FileWriter;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by mahdiaza on 02/04/17.
 */
public class ReaderTest extends TestCase {

  public void setUp() throws Exception {
    super.setUp();
  }

  @Test
  public void testParse_nullInput(){
    Reader reader = new Reader();
    boolean result = reader.parse(null);
    assertEquals(false, result);
  }

  @Test
  public void testParse_emptyInput(){
    Reader reader = new Reader();
    boolean result = reader.parse("");
    assertEquals(false, result);
  }

  @Test
  public void testParse_invalidLocationPath(){
    Reader reader = new Reader();
    boolean result = reader.parse("./this/is/invalid/path");
    assertEquals(false, result);
  }

  @Test
  public void testGetEmails_InputHasBlankLines() throws Exception {
    FileWriter testEmails = new FileWriter("./src/main/resources/testInputs.txt");
    String input = "t1@tuv.coo";
    testEmails.write("\n\n" + input + "\n\n");
    testEmails.close();
    Reader reader = new Reader();
    reader.parse("./src/main/resources/testInputs.txt");
    String actual = reader.getEmails().get(0).toString();
    assertEquals(input, actual);
  }

}