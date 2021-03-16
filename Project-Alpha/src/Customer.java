public class Customer {
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
                + "\nID Proof: " + this.idProof + "\n"
                +"\nRoom Number:"+ this.roomNumber
                +"\nBooking ID:"+ this.bookingID
                +"\nBooking Status:"+ this.bookingStatus;
    }

    public static void main(String[] args) {
        int numErrors = 0;

        Customer C1 = new Customer("Virat", "306-603-3061", "ABC456");

        if (!C1.getName().equals("Virat")) {
            System.out.println("Constructor or getName() failed: The customer's name" +
                    " should have been Virat instead of " + C1.getName());
            numErrors++;
        }
        if (!C1.getContactNumber().equals("306-603-3061")) {
            System.out.println("Constructor or getContactNumber() failed: The customer's contact " +
                    "number should have been 306-603-3061 instead of " + C1.getContactNumber());
            numErrors++;
        }
        if (!C1.getIdProof().equals("ABC456")) {
            System.out.println("Constructor or getIdProof() failed: The customer's proof " +
                    "id should have been ABC456 instead of " + C1.getIdProof());
            numErrors++;
        }

        String expected1 = "\nName: Virat\n" +
                "Contact number: 306-603-3061\n" +
                "ID Proof: ABC456\n";

        if (!C1.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + C1);
            numErrors++;
        }

        Customer C2 = new Customer("Spider Man", "999-999-9999", "SPI988");

        if (!C2.getName().equals("Spider Man")) {
            System.out.println("Constructor or getName() failed: The customer's name" +
                    " should have been Spider Man instead of " + C1.getName());
            numErrors++;
        }
        if (!C2.getContactNumber().equals("999-999-9999")) {
            System.out.println("Constructor or getContactNumber() failed: The customer's contact " +
                    "number should have been 999-999-9999 instead of " + C1.getContactNumber());
            numErrors++;
        }
        if (!C2.getIdProof().equals("SPI988")) {
            System.out.println("Constructor or getIdProof() failed: The customer's proof " +
                    "id should have been SPI988 instead of " + C1.getIdProof());
            numErrors++;
        }

        expected1 = "\nName: Spider Man\n" +
                "Contact number: 999-999-9999\n" +
                "ID Proof: SPI988\n";

        if (!C2.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + C2);
            numErrors++;
        }

        C2.setName("Vision");
        C2.setContactNumber("111-111-1111");
        C2.setIdProof("VIS384");

        expected1 = "\nName: Vision\n" +
                "Contact number: 111-111-1111\n" +
                "ID Proof: VIS384\n";

        if (!C2.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + C2);
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}