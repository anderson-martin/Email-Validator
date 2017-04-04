import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 * Email Validator
 *
 * <p>This application receives list of emails as user input, validates them against their syntax as
 * well as their domain name, classifies and eventually writes them into output files.
 *
 * <p>After a successful run, the app creates three output files (as text) in the {@code ./target}
 * directory, and writes the newly-classified emails into them.
 *
 * @author Martin Anderson
 */
public class Main {
  /**
   * User should write to-be-classified emails into the file {@code resources/userInputs.txt}, then
   * run the app. Each line should include only one email address. The {@userInput} file already
   * includes list of emails, for demo purpoose.
   *
   * <p>Alternatively, user can change input file by putting the file's relative location as
   * argument for {@link Reader#parse(String)}.
   *
   * @param args can be left empty
   * @exception NamingException If an exception occurs during operations with DirContext object.
   *     This is because {@link Classifier#Classifier()} can throw this exception.
   * @exception java.io.FileNotFoundException if {@code Reader} can't find the input file. This
   *     exception will be handled by {@link Reader#parse(String)} but for the sake of clarity, it's
   *     mentioned here, too.
   * @exception IOException if there is a problem during parse of the input file. These exceptions
   *     will be handled by {@link Reader#parse(String)} but for the sake of clarity, it's mentioned
   *     here, too.
   */
  public static void main(String[] args) {
    Logger logger = Logger.getAnonymousLogger();
    Reader reader = new Reader();
    reader.parse("./src/main/resources/userInputs.txt");
    try {
      Classifier classifier = new Classifier();
      classifier.start(reader.getEmails());
    } catch (NamingException e) {
      logger.log(Level.SEVERE, "Couldn't create Classifier's object: ", e);
    }
    System.out.println(
        "Validation done successfully. "
            + "Please look into text output files located in ./target directory.");
  }
}
