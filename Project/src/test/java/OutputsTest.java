import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import junit.framework.TestCase;

/**
 * Created by mahdiaza on 03/04/17.
 */
public class OutputsTest extends TestCase {

  public void setUp() throws Exception {
    super.setUp();
  }

  public void testGetNumber() {
    assertEquals(1, Outputs.one.getNumber());
  }

  public void testGetNumber_unmatchedEnumValueAndInt() {
    assertThat(Outputs.three.getNumber(), is(not(2)));
  }
}