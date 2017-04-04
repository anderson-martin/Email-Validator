/**
 * Each enum value represents one of the output files, in which the results will be printed.
 *
 * @author Martin Anderson
 */
enum Outputs {
  /** represents the first output file. It contains emails which have invalid syntax. */
  one(1),
  /**
   * represents second output file. It contains emails which have valid syntax but invalid domain
   * name.
   */
  two(2),
  /** represents third output file. It contains emails which have valid syntax and domain name. */
  three(3);
  private int number;

  /**
   * Assigns numeric value to this enum value.
   *
   * <p>The resulting class field {@code number} will be used by {@link Writer} object to write
   * newly-classified email into apropriate output file, and finally to close the correspondent
   * output stream into it.
   *
   * @param number (as int). To avoid confusion, it's recommended to use the value correspondent to
   *     this enum. For example, enum {@code three} should have {@code number} with value of 3.
   */
  Outputs(int number) {
    this.number = number;
  }

  /**
   * @return class field {@code number}.
   */
  int getNumber() {
    return this.number;
  }
}
