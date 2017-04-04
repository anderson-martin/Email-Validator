import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.FileWriter;
import junit.framework.TestCase;

/**
 * Created by mahdiaza on 02/04/17.
 */
public class WriterTest extends TestCase {
  private Writer writer;

  public void setUp() throws Exception {
    super.setUp();
    this.writer = new Writer();
  }

  public void testClassifyAs_invalidClassification() {
      Email email1 = new Email("email1@example.com");
      boolean output = this.writer.write(email1);
      assertEquals(false, output);
  }

  public void testCloseStream(){
    Writer writer = new Writer();
    writer.createOutputFiles();
    assertEquals(true, writer.closeStreams());
  }

  public void testCreateOutputFiles(){
    Writer writer = new Writer();
    FileWriter[] actual =   writer.createOutputFiles();
    assertThat(actual.length, is(not(0)));
  }
}