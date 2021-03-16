/**
 * An interface for input and output methods.
 */
public interface InputOutputInterface {
    /**
     *
     * @param prompt
     * @return
     */
    String readString(String prompt);

    /**
     *
     * @param prompt
     * @return
     */
    int readInt(String prompt);

    /**
     *
     * @param options
     * @return
     */
    int readChoice(String[] options);

    /**
     *
     * @param outString
     */
    void outputString(String outString);
}
