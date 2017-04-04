import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Reader object opens input file, reads the content and saves it into a list. */
class Reader {
  private final List<Email> emails;
  private final Logger logger;

  /**
   * Initializes a list to store emails, opens the input file, starts parsing emails and finally
   * adds the retrieved emails into the list.
   */
  Reader() {
    this.logger = Logger.getAnonymousLogger();
    this.emails = new ArrayList<Email>();
  }

  /**
   * Opens input file with a given location address, reads emails line by line and saves them into a
   * list.
   *
   * @param inputLocation relative path of the input file(as String)
   * @exception FileNotFoundException occurs if it can't find a file with the given location path.
   * @exception IOException can occur when BufferedReader tries to read lines.
   * @return true, if parsing done successfully. Returns false if input is null, empty or if an
   *     exception thrown.
   */
  boolean parse(String inputLocation) {
    if ((inputLocation == null) || (inputLocation == "")) {
      return false;
    }

    FileReader inputFile;
    try {
      inputFile = new FileReader(inputLocation);
      BufferedReader bufferedReader = new BufferedReader(inputFile);
      String emailAddress;
      while ((emailAddress = bufferedReader.readLine()) != null) {
        if (!emailAddress.equals("")) {
          this.emails.add(new Email(emailAddress));
        }
      }
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "Couldn't find the input file: ", e);
      return false;
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Couldn't parse the input file: ", e);
      return false;
    }
    return true;
  }

  /**
   * Gets list of parsed emails.
   *
   * @return class field {@code emails} (as an ArrayList)
   */
  List<Email> getEmails() {
    return this.emails;
  }
}
