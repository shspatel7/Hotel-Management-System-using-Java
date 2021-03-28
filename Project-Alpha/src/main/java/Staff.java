public class Staff{

    /**
     * Name of the staff member
     */
    private String name;

    /**
     * Identification of the staff memeber
     */
    private String staffId;

    /**
     * Designation assigned to staff member
     */
    private String designation;

    /**
     * Staff member user ID for login
     */
    private String userId;

    /**
     * Password for login
     */
    private String password;

    /**
     *
     * @param name Name of staff member
     * @param staffId Identification of the member
     * @param designation Post assigned to the member
     * @param userId userID for login
     * @param password password for login in to the system
     */
    public Staff(String name, String staffId, String designation, String userId, String password)
    {
        this.name = name;
        this.staffId = staffId;
        this.designation = designation;
        this.userId = userId;
        this.password = password;
    }

    /**
     * A method that gets the name of the member
     * @return Return the name of the member
     */
    public String getName() {
        return name;
    }

    /**
     * A method to set the name of the member
     * @param name Name of the member
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method that gets the staff ID
     * @return return the staff ID
     */
    public String getStaffId(){
        return this.staffId;
    }

    /**
     * A method to set the staff Id
     * @param staffId set the ID for the staff member
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * A method that get the designation of the member
     * @return Returns the designation of the member
     */
    public String getDesignation() {
        return this.designation;
    }

    /**
     * A method to set the designation for the member
     * @param designation Sets the designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * A method to get the userId of the member
     * @return Return the user ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * A method to set the userID for the staff member
     * @param userId Set the userID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * A method to get the password of the staff member
     * @return Returns the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * A method to set the password for the member to login to the system
     * @param password Password to login to the system
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * A method that gives the string representaion of the staff member
     * @return Returns a String of Name, Id , and designation
     */
    public String toString()
    {
        return "\nName: " + this.name + "\nStaffId: " + this.staffId
                + "\nDesignation: " + this.designation + "\n";
    }
}