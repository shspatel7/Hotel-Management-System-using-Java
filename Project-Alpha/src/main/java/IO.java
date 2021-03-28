import javax.swing.*;

/**
 *  It is JOptionPane implementation of the IO.
 * @author Shreyul
 */
public class IO implements InputOutputInterface
{
    /**
     * Display the list of options and read an int that is the index of one of the options. The
     * first option is the default.
     * @param options an array with the options that are presented to the user
     * @return the int specifying the array index for the option selected by the user
     */

    public int readChoice(String[] options) {
        String selection = (String) JOptionPane.showInputDialog(null, // parent component
                "Please select an option ", // prompt
                "Motel Management System", // window title
                JOptionPane.QUESTION_MESSAGE, // type of message
                null, // icon displayed
                options, // choices for the Combo box
                options[0]); // initial selection
        if (selection == null)
            return 0; // Cancel or X button clicked
        for (int i = 0; i < options.length; i++)
            if (selection.equals(options[i]))
                return i;
        JOptionPane.showMessageDialog(null, "Illegal choice: " + selection + "\n");
        return readChoice(options);

    }
    
    

    /**
     * A method that uses JOptionPane to acquire input from the user, and returns a String
     * @param prompt the string to be displayed as a prompt
     * @return
     */
    @Override
    public String readString(String prompt)
    {

        String input = JOptionPane.showInputDialog(null, prompt);
        String output;
        if (input != null && input.length() > 0)
        {
            try
            {
                output = input;
            } catch (NumberFormatException e)
            {
                output = readString("Given Value is not a String!\n" + prompt);
            }
        }
        else
        {
            output = readString("Please enter a valid String value!\n"+prompt);
        }
        return output;
    }

    /**
     * A method that uses JOptionPane to acquire input from the user, and returns an integer.
     * @param prompt the string to be displayed as a prompt
     * @return
     */
    @Override
    public int readInt(String prompt)
    {
        String input = JOptionPane.showInputDialog(null, prompt);
        int output;
        if (input != null && input.length() > 0)
        {
            try
            {
                output = Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {

                output = readInt("Given input is invalid!\n"+prompt);
            }
        }
        else
        {
            output = readInt("Please enter a valid Integer input!\n" + prompt);
        }
        return output;
    }

    /**
     *A method that uses JOptionPane to display the given string to the user.
     * @param outString the string whose value is to be displayed
     */
    @Override
    public void outputString(String outString)
    {
        JOptionPane.showMessageDialog(null, outString);
    }

    /**
     *  The main method to tests the above method.
     * @param args
     */
    public static void main(String[] args)
    {
        InputOutputInterface IO = new IO();

        String string1 = IO.readString("Please enter a String input: ");
        IO.outputString(string1);
        int int1 = IO.readInt("Please enter an Integer input: ");
        IO.outputString(Integer.toString(int1));

        String string2 = "input";
        int int2 = 23;

        int errors = 0;
        if (!string1.equals(string2))
        {
            IO.outputString("Error found in readString() function.\n");
            IO.outputString("Expected: "+string2+" but found "+string1+"\n");
            errors += 1;
        }

        if (int1 != int2)
        {
            IO.outputString("Error found in readInt() function.\n");
            IO.outputString("Expected: "+int2+" but found "+int1+"\n");
            errors += 1;
        }
        IO.outputString("-----Testing Completed ------");
        IO.outputString("The number of errors found: " + errors);
    }
}