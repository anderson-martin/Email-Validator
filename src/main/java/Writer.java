import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Writer object creates output files, and writes given {@link Email} object into its correspondent
 * output file. It can also closes output streams.
 *
 * @author Martin Anderson
 */
class Writer {
  private final Logger logger;
  private final FileWriter[] outputFiles;

  /**
   * initializes {@code Logger} object as well as an Array which holds reference to all the output
   * files.
   *
   * <p>Eventhough there are three output files, but for convenience, this creates an array with
   * size 3+1. By this way, {@link #write(Email)} can match {@code Email} objects to output files
   * which have same index number and {@link Email#getOutput()}. For example, Email object with enum
   * value of three(3) has to be written in the output file with index value of 3.
   */
  Writer() {
    this.logger = Logger.getAnonymousLogger();
    this.outputFiles = new FileWriter[4];
  }

  /**
   * Writes a given email into a proper output file. Index value of the file matches with {@link
   * Email#getOutput()}. For example, Email object with enum value of three(3) has to be written in
   * the output file with index value of 3.
   *
   * @param email Given input by user(as Email object)
   * @return true if email address is successfully written in the output file. Returns false if
   *     input is invalid or an exception is thrown.
   */
  boolean write(Email email) {

    if ((email == null) || (email.getOutput() == null)) {
      return false;
    }
    int outputFileNumber = email.getOutput().getNumber();
    try {
      this.outputFiles[outputFileNumber].write(email.toString() + "\n");
    } catch (IOException e) {
      logger.log(
          Level.INFO,
          "Email address couldn't be written in output file " + outputFileNumber + " : ",
          e);
      return false;
    } catch (NullPointerException e) {
      logger.log(Level.WARNING, "Requested output file not found: ", e);
      return false;
    }
    return true;
  }

  /**
   * Closes output streams into the output files
   *
   * @return true if all streams were close successfully. Else returns false.
   */
  boolean closeStreams() {
    try {
      outputFiles[1].close();
      outputFiles[2].close();
      outputFiles[3].close();
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Output stream hasn't been closed: ", e);
      return false;
    }
    return true;
  }

  /**
   * Creates first version of all output files.
   *
   * @return Array containing reference to output files, if everything goes fine. Else returns an
   *     array with zero size.
   */
  FileWriter[] createOutputFiles() {
    try {
      FileWriter output1 = new FileWriter("./target/output1.txt");
      output1.write("OUTPUT 1 - Following addresses have invalid syntax: \n");
      outputFiles[1] = output1;
      FileWriter output2 = new FileWriter("./target/output2.txt");
      output2.write("OUTPUT 2 - Following addresses have valid syntax but invalid domain name: \n");
      outputFiles[2] = output2;
      FileWriter output3 = new FileWriter("./target/output3.txt");
      output3.write("OUTPUT 3 - " + "Following addresses have valid syntax and domain name: \n");
      outputFiles[3] = output3;
      return outputFiles;
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Output file(s) hasn't been created: ", e);
      return new FileWriter[0];
    }
  }
}
