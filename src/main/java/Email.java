/**
 * Email object consists of an email address and enum value {@code output}.
 *
 * @author Martin Anderson
 */
class Email {
  private String email;
  private Outputs output;

  /**
   * Creates email object based on the given parameter..
   *
   * @param email address that the email object should be based on (as String)
   */
  Email(String email) {
    this.email = email;
  }

  /**
   * Splits class variable {@code email} around matches of @ sign.
   *
   * <p>This method gets called by {@link Classifier#classify(Email)}; There, this {@code email}
   * will be firstly checked against class variable {@code EMAIL_PATTERN}. By this way,
   * syntactically invalid emails will be classified and written in the proper output file, too.
   *
   * @return domain name associated with this email(as String). In case of invalid result, it
   *     returns a String "invalidEmailAddress".
   */
  String getHostName() {
    String[] temp = email.split("@");
    if (temp.length == 2) {
      return temp[1];
    }
    return "invalidEmailAddress";
  }

  /**
   * @return enum value of this email.
   * */
  Outputs getOutput() {
    return this.output;
  }

  boolean setOutput(Outputs output) {
    this.output = output;
    return true;
  }

  /**
   * @return class variable {@code email}.
   * */
  @Override
  public String toString() {
    return this.email;
  }
}
