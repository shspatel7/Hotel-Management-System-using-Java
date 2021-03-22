public class Customer{
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
     * The customer's room number
     */
    private int roomNumber;

    /**
     * Customer's Booking ID
     */
    private int bookingID;

    /**
     * Customer's Booking status
     */
    private String bookingStatus;

    /**
     *
     * @param cName Name of the customer
     * @param cNumber Contact number of the customer
     * @param id Customer's proof id
     */
    public Customer(String cName, String cNumber, String id) {
        this.name = cName;
        this.contactNumber = cNumber;
        this.idProof = id;
        this.roomNumber = -1;
        this.bookingID = -1;
        this.bookingStatus= null;
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
     * Gets the default room number of the customer
     * @return the room number of the customer.
     */
    public int getRoomNumber()
    {
        return this.roomNumber;
    }

    /**
     * Sets the room number of the customer.
     * @param roomNumber
     */
    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    /**
     * An accessor method that returns customer's booking id.
     * @return the booking ID of the customer
     */
    public int getBookingID()
    {
        return this.bookingID;
    }

    /**
     * An accessor method that returns customer's booking status
     * @return booking status of the customer
     */
    public String getBookingStatus()
    {
        return this.bookingStatus;
    }

    /**
     * Sets the customer's booking status
     * @param bookingStatus of the customer
     */
    public void setBookingStatus(String bookingStatus)
    {
        this.bookingStatus = bookingStatus;
    }

    /**
     * Sets the customer's booking ID
     * @param bookingID of the customer
     */
    public void setBookingID(int bookingID)
    {
        this.bookingID = bookingID;
    }

    /**
     * Return a string representation of the customer.
     *
     * @return a string representation of the customer.
     */
    public String toString() {
        return "\nName: " + this.name + "\nContact number: " + this.contactNumber
                + "\nID Proof: " + this.idProof
                +"\nRoom Number:"+ this.roomNumber
                +"\nBooking ID:"+ this.bookingID
                +"\nBooking Status:"+ this.bookingStatus;
    }
}