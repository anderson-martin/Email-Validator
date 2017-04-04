import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import junit.framework.TestCase;
import org.junit.Test;

public class MainTest extends TestCase {
  private final Logger logger;
  private final Reader reader;
  private final Classifier classifier;

  /**
   * This object should be caught upon creation, as this can throw exception.
   *
   * @exception NamingException can occur during operation with DirContext object.
   */

  public MainTest() throws NamingException {
    this.logger = Logger.getAnonymousLogger();
    reader = new Reader();
    this.classifier = new Classifier();
  }

  public void setUp() throws Exception {
    super.setUp();
  }

  @Test
  public void testMain_emailWithInvalidSyntax()  {
    String input = "%@tuv.coo";
    createFileForTestInputs("%@tuv.coo");
    runMainApplication();
    String resultedEmail = getPrintedEmail("./target/output1.txt");
    assertEquals(input, resultedEmail);
  }

  @Test
  public void testMain_emailWithInvalidDomainName(){
    String input = "test@tuv.coxyuoi";
    createFileForTestInputs(input);
    runMainApplication();
    String output = getPrintedEmail("./target/output2.txt");
    assertEquals(input, output);
  }

  @Test
  public void testMain_validEmailAddress() {
    String input = "john@yahoo.com";
    createFileForTestInputs(input);
    runMainApplication();
    String output = getPrintedEmail("./target/output3.txt");
    assertEquals(input, output);
  }

  private FileWriter createFileForTestInputs(String email) {
    FileWriter testEmails = null;
    try {
      testEmails = new FileWriter("./src/main/resources/testInputs.txt");
      testEmails.write(email);
      testEmails.close();
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Couldn't create input file (for testing): ", e);
    }
    return testEmails;
  }

  private String getPrintedEmail(String outputFileLocation) {
    FileReader outputFile = null;
    BufferedReader bufferedReader = null;
    String printedEmail = "";
    try {
      outputFile = new FileReader(outputFileLocation);
      bufferedReader = new BufferedReader(outputFile);
      while (bufferedReader.readLine() != null) {
        printedEmail = bufferedReader.readLine();
      }
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "Couldn't find the output file (for testing): ", e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Couldn't read the output file (for testing): ", e);
    }
    return printedEmail;
  }

  private void runMainApplication() {
    reader.parse("./src/main/resources/testInputs.txt");
    this.classifier.start(reader.getEmails());
  }
}
