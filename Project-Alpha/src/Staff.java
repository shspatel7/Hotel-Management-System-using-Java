public class Staff {

    private String name;
    private String staffId;
    private String designation;
    private String userId;
    private String password;

    public Staff(String name, String staffId, String designation, String userId, String password)
    {
        this.name = name;
        this.staffId = staffId;
        this.designation = designation;
        this.userId = userId;
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffId(){
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString()
    {
        return "\nName: " + this.name + "\nStaffId: " + this.staffId
                + "\nDesignation: " + this.designation + "\n";
    }
}