import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by mahdiaza on 02/04/17.
 */
public class EmailTest extends TestCase {

  public void setUp() throws Exception {
    super.setUp();
  }

  @Test
  public void testEmailAndToString() {
    Email email = new Email("m@mp4.com");
    assertEquals("m@mp4.com", email.toString());
  }

  @Test
  public void testGetHostName() {
    Email email = new Email("m@mp4.com");
    assertEquals("mp4.com", email.getHostName());
  }

  @Test
  public void testGetHostName_addressWithDoubleAtSign() {
    Email email = new Email("m@mp@4.com");
    assertEquals("invalidEmailAddress", email.getHostName());
  }

  @Test
  public void testGetHostName_addressWithEmptySpace() {
    Email email = new Email("yt @  uy.xo");
    assertEquals("  uy.xo", email.getHostName());
  }

  @Test
  public void testSetAndGetOutput() {
    Email email = new Email("gfhg@hjkghjfu.yuy");
    email.setOutput(Outputs.one);
    Enum actual = email.getOutput();
    assertEquals(Outputs.one, actual);
  }
}