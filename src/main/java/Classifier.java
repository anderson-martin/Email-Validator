import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Classifier object accepts list of emails, validates each of them and classifies it properly.
 *
 * @author Martin Anderson
 */
class Classifier {
  private final String EMAIL_PATTERN;
  private final Hashtable<String, String> environment;
  private final DirContext directoryContext;
  private final Logger logger;
  private final Writer writer;
  private Attributes attributes;

  /**
   * Instantiates class fields
   *
   * @exception NamingException if an exception occurs during creation of {@code DirContext} object.
   *     This exception should be handled by the methods that call {@code Classifier()}
   */
  Classifier() throws NamingException {
    this.logger = Logger.getAnonymousLogger();
    this.environment = new Hashtable<String, String>();
    environment.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
    this.directoryContext = new InitialDirContext(environment);
    this.EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    this.writer = new Writer();
  }

  /**
   * Asks this {@code Writer } to create output files, loops through a email list, classifies and
   * write them into apropriate output files, and eventually asks this {@code Writer} to close
   * output streams.
   *
   * @exception Exception will be handled by inner methods. *
   * @param emails list of emails given by user
   * @return true if the whole process done successfully. Any problem with {@code
   *     writer.write(email)} will result in false return.
   */
  boolean start(List<Email> emails) {
    this.writer.createOutputFiles();
    if ((emails == null) || (emails.size() < 1)) {
      return false;
    }

    for (Email email : emails) {
      classify(email);
      if (!writer.write(email)) {
        return false;
      }
      ;
    }
    writer.closeStreams();
    return true;
  }

  /**
   * Validates email against its syntax & domain name and sets proper enum value.
   *
   * @param email email object
   * @return -1 if input is null or doesnt pass next checks. Returns 1 if given email has invalid
   *     syntax. Returns 2 if given email has valid syntax but null or invalid hostname. Returns 3
   *     if email has valid syntax and domain name.
   */
  private int classify(Email email) {
    if (email == null) {
      return -1;
    }

    if (!syntaxIsFine(email)) {
      email.setOutput(Outputs.one);
      return 1;
    }

    if ((getMXrecords(email) == null) || (!domainIsValid(attributes))) {
      email.setOutput(Outputs.two);
      return 2;
    }

    if (email.setOutput(Outputs.three)) {
      return 3;
    }

    return -1;
  }

  /**
   * Ideally, a single letter should not identify a user in a domain or url, but string
   * implementation allows it to be a single letter. So I have let this be otherwise, I would have
   * checked the length.
   *
   * @return true if email matches regular expression {@code EMAIL_PATTERN}. In case not, returns
   *     false.
   */
  private boolean syntaxIsFine(Email email) {
    return email.toString().matches(EMAIL_PATTERN);
  }

  private boolean domainIsValid(Attributes attributes) {
    return (attributes.get("MX") != null);
  }

  /**
   * Looks for available Hostnames within MX record.
   *
   * @param email given Email object by user
   * @return directory object's attributes, upon success. And returns null, if it cannot find any
   *     associated Hostname. That's why returned value of this method should always be checked
   *     against null value.
   */
  private Attributes getMXrecords(Email email) {
    try {
      attributes = directoryContext.getAttributes(email.getHostName(), new String[] {"MX"});
      return attributes;
    } catch (NamingException e) {
      logger.log(Level.INFO, "Found an invalid email address: ", e);
    }
    return null;
  }
}
