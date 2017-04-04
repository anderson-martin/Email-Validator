import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import junit.framework.TestCase;

public class ClassifierTest extends TestCase {
  private final Logger logger;
  private final Classifier classifier;
  private final Reader reader;

  /**
   * This object can throw exception, and it should be caught upon creation call.
   *
   * @exception NamingException can occur during operation with DirContext object.
   */
  public ClassifierTest() throws NamingException {
    this.logger = Logger.getAnonymousLogger();
    this.classifier = new Classifier();
    reader = new Reader();
  }

  public void setUp() throws Exception {
    super.setUp();
  }

  public void testClassfier_catchExceptionDuringInitialization() {
    boolean hadError = false;
    try {
      new Classifier();
    } catch (NamingException e) {
      logger.log(Level.INFO, "Couldn't create Classifier (for testing): ", e);
      hadError = true;
    }
    assertEquals(false, hadError);
  }

  public void testStart() {
    List<Email> emails = new ArrayList<Email>();
    emails.add(new Email("john@doe.com"));
    emails.add(new Email("%@doe.com"));
    boolean successfulStart = classifier.start(emails);
    assertEquals(true, successfulStart);
  }

  public void testStart_nullListAsInput() {
    List<Email> emails = null;
    boolean successfulStart = classifier.start(emails);
    assertEquals(false, successfulStart);
  }

  public void testStart_nullEmailInsideGivenArrayList() {
    List<Email> emails = new ArrayList<Email>();
    emails.add(null);
    boolean successfulStart = classifier.start(emails);
    assertEquals(false, successfulStart);
  }

}
