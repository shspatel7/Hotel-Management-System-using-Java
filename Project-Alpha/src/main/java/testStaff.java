public class testStaff {
    public static void main(String[] args) {
        int numErrors = 0;

        Staff S1 = new Staff("Fiza Patel", "123", "Receptionist", "FIP428", "Bhangi12");

        if (!S1.getName().equals("Fiza Patel")) {
            System.out.println("Constructor or getName() failed: The staff's name " +
                    " should have been Fiza Patel instead of " + S1.getName());
        }
        if (!S1.getStaffId().equals("123")) {
            System.out.println("Constructor or getStaffId() failed: The staff's id " +
                    "number should have been 123 instead of " + S1.getStaffId());
            numErrors++;
        }
        if (!S1.getDesignation().equals("Receptionist")) {
            System.out.println("Constructor or getDesignation() failed: The staff's designation " +
                    "should have been Receptionist instead of " + S1.getDesignation());
            numErrors++;
        }
        if (!S1.getUserId().equals("FIP428")) {
            System.out.println("Constructor or getUserId() failed: The staff's user id " +
                    "number should have been FIP428 instead of " + S1.getUserId());
            numErrors++;
        }
        if (!S1.getPassword().equals("Bhangi12")) {
            System.out.println("Constructor or getPassword() failed: The staff's password " +
                    "should have been Bhangi12 instead of " + S1.getDesignation());
            numErrors++;
        }

        String expected1 = "\nName: Fiza Patel\n" +
                "StaffId: 123\n" +
                "Designation: Receptionist\n";

        if (!S1.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + S1);
            numErrors++;
        }

        Staff S2 = new Staff("Tony Stark", "111", "Manager", "TON123", "IronMan1");

        if (!S2.getName().equals("Tony Stark")) {
            System.out.println("Constructor or getName() failed: The staff's name " +
                    " should have been Tony Stark instead of " + S2.getName());
        }
        if (!S2.getStaffId().equals("111")) {
            System.out.println("Constructor or getStaffId() failed: The staff's id " +
                    "number should have been 111 instead of " + S1.getStaffId());
            numErrors++;
        }
        if (!S2.getDesignation().equals("Manager")) {
            System.out.println("Constructor or getDesignation() failed: The staff's designation " +
                    "should have been Manager instead of " + S1.getDesignation());
            numErrors++;
        }
        if (!S2.getUserId().equals("TON123")) {
            System.out.println("Constructor or getUserId() failed: The staff's user id " +
                    "number should have been TON123 instead of " + S1.getUserId());
            numErrors++;
        }
        if (!S2.getPassword().equals("IronMan1")) {
            System.out.println("Constructor or getPassword() failed: The staff's password " +
                    "should have been IronMan1 instead of " + S1.getDesignation());
            numErrors++;
        }

        String expected2 = "\nName: Tony Stark\n" +
                "StaffId: 111\n" +
                "Designation: Manager\n";

        if (!S2.toString().equals(expected2)) {
            System.out.println("toString() failed: Expected: " + expected2 + " but returned " + S2);
            numErrors++;
        }

        S1.setName("Hardik");
        S1.setStaffId("254");
        S1.setDesignation("Receptionist");
        S1.setUserId("HAR901");
        S1.setPassword("Power123");

        expected1 = "\nName: Hardik\n" +
                "StaffId: 254\n" +
                "Designation: Receptionist\n";

        if (!S1.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + S1);
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
