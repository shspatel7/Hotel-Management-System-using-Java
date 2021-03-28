/**
 * An interface for input and output methods.
 */
public interface InputOutputInterface {
    /**
     * A method that read string value for the user.
     * @param prompt
     * @return
     */
    String readString(String prompt);

    /**
     * A method that reads integer value from the user.
     * @param prompt
     * @return
     */
    int readInt(String prompt);

    /**
     * A method that reads the choice from the user
     * @param options
     * @return
     */
    int readChoice(String[] options);

    /**
     * A method to output string value from the user.
     * @param outString
     */
    void outputString(String outString);
}