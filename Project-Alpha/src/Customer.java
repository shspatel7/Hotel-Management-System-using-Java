public class Customer
{
    /**
     * The name of the Customer.
     */
    private String name;

    /**
     * The Customer's contact number.
     */
    private String contactNumber;

    /**
     * The Customer's id Proof
     */
    private String idProof;


    /**
     * The constructor method for the customer class.
     * @param cName Name of the customer
     * @param cNumber Contact number of the customer
     * @param id id proof of the customer
     */
    public Customer(String cName, String cNumber, String id) {
        this.name = cName;
        this.contactNumber = cNumber;
        this.idProof = id;
    }

    /**
     * Return the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the Contact number of the Customer.
     *
     * @return the contact number of the customer.
     */
    public String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * Return the idProof of the customer
     * @return id of the customer
     */
    public String getIdProof()
    {
        return this.idProof;
    }

    /**
     * Change the name of the customer.
     * @param newName the name of the customer
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the contact number of the customer.
     * @param cNumber contact number of customer.
     */
    public void setContactNumber(String cNumber)
    {
        contactNumber = cNumber;
    }

    /**
     * Sets the id proof of the customer.
     * @param id proof of the customer.
     */
    public void setIdProof(String id)
    {
        idProof = id;
    }

    /**
     * Return a string representation of the customer.
     *
     * @return a string representation of the customer.
     */
    public String toString() {
        return "\nName: " + this.name + "\nContact number: " + this.contactNumber
                + "\nID Proof: " + this.idProof + "\n";
    }
}