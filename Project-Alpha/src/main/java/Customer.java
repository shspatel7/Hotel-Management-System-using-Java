import java.time.LocalDate;

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
     * Customer's check IN date
     */
    private LocalDate checkInDate;

    /**
     * Customer's check OUT date
     */
    private LocalDate checkOutDate;

    /**
     * Customer's Amount Due.
     */
    private double amountDue;

    /**
     * The room rate for the customer's room.
     */
    private double roomRate;

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
        this.checkInDate = null;
        this.checkOutDate = null;
        this.amountDue = 0;
        this.roomRate = 0;
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
     * The method that returns the customer's check IN date.
     * @return the check IN date
     */
    public LocalDate getCheckInDate()
    {
        return this.checkInDate;
    }

    /**
     * The method that returns the customer's check OUT date.
     * @return the check OUT date
     */
    public LocalDate getCheckOutDate()
    {
        return this.checkOutDate;
    }

    /**
     * A method to set checkIN date
     * @param date
     */
    public void setCheckInDate(LocalDate date)
    {
        this.checkInDate = date;
    }

    /**
     * A method to set checkOUT date
     * @param date
     */
    public void setCheckOutDate(LocalDate date)
    {
        this.checkOutDate = date;
    }

    /**
     * A method that returns the amount which is due to Customer.
     * @return the amount due
     */
    public double getAmountDue()
    {
        return this.amountDue;
    }

    /**
     * The method to set customer's amount due
     * @param amountDue to customer
     */
    public void setAmountDue(double amountDue)
    {
        if (amountDue < 0){
            throw new IllegalArgumentException("The amount: "+ amountDue +" cannot be negative.");
        }
        if (amountDue == 0)
        {
            this.amountDue = 0;
        }
        this.amountDue += amountDue;
    }

    /**
     * A method to get the room rate assigned to the customer's booking.
      * @return the room rate of customer's booking.
     */
    public double getRoomRate()
    {
        return this.roomRate;
    }

    /**
     *A method to set the room rate for the customer.
     * @param roomRate assigned to the customer's booking.
     */
    public void setRoomRate(double roomRate)
    {
        if (roomRate < 0)
        {
            throw new IllegalArgumentException("The given room rate: "+roomRate+" cannot be negative.");
        }
        this.roomRate = roomRate;
    }

    /**
     * Return a string representation of the customer.
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