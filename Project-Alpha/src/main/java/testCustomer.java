import java.time.LocalDate;

public class testCustomer
{
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

        C1.setRoomNumber(103);
        if (C1.getRoomNumber() != 103) {
            System.out.println("setRoomNumber() or getRoomNumber() failed: The customer's Room " +
                    "Number should have been 103 instead of " + C1.getRoomNumber());
            numErrors++;
        }

        C1.setBookingID(9876);
        if (C1.getBookingID() != 9876) {
            System.out.println("setBookingID() or getBookingID() failed: The customer's booking " +
                    "id should have been AB9876 instead of " + C1.getBookingID());
            numErrors++;
        }

        C1.setBookingStatus("Confirmed");
        if (!C1.getBookingStatus().equals("Confirmed")) {
            System.out.println("setBookingStatus() or getBookingStatus() failed: The customer's booking " +
                    "status should have been Confirmed instead of " + C1.getBookingStatus());
            numErrors++;
        }

        C1.setAmountDue(199.99);
        if (C1.getAmountDue() != 199.99) {
            System.out.println("setAmountDue() or getAmountDue() failed: The customer's amount due " +
                    "should have been 199.99 instead of " + C1.getAmountDue());
            numErrors++;
        }

        String expected1 = "\nName: Virat\n" +
                "Contact number: 306-603-3061\n" +
                "ID Proof: ABC456\n" +
                "Room Number:103\n" +
                "Booking ID:9876\n" +
                "Booking Status:Confirmed";

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

        C2.setRoomNumber(100);
        if (C2.getRoomNumber() != 100) {
            System.out.println("setRoomNumber() or getRoomNumber() failed: The customer's Room " +
                    "Number should have been 100 instead of " + C2.getRoomNumber());
            numErrors++;
        }

        C2.setBookingID(4578);
        if (C2.getBookingID() != 4578) {
            System.out.println("setBookingID() or getBookingID() failed: The customer's booking " +
                    "id should have been TU4578 instead of " + C2.getBookingID());
            numErrors++;
        }

        C2.setBookingStatus("Confirmed");
        if (!C2.getBookingStatus().equals("Confirmed")) {
            System.out.println("setBookingStatus() or getBookingStatus() failed: The customer's booking " +
                    "status should have been Confirmed instead of " + C2.getBookingStatus());
            numErrors++;
        }

        C2.setAmountDue(249.99);
        if (C2.getAmountDue() != 249.99) {
            System.out.println("setAmountDue() or getAmountDue() failed: The customer's amount due " +
                    "should have been 199.99 instead of " + C2.getAmountDue());
            numErrors++;
        }

        expected1 = "\nName: Spider Man\n" +
                "Contact number: 999-999-9999\n" +
                "ID Proof: SPI988\n" +
                "Room Number:100\n" +
                "Booking ID:4578\n" +
                "Booking Status:Confirmed";

        if (!C2.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + C2);
            numErrors++;
        }

        C2.setName("Vision");
        C2.setContactNumber("111-111-1111");
        C2.setIdProof("VIS384");

        expected1 = "\nName: Vision\n" +
                "Contact number: 111-111-1111\n" +
                "ID Proof: VIS384\n" +
                "Room Number:100\n" +
                "Booking ID:4578\n" +
                "Booking Status:Confirmed";

        if (!C2.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + C2);
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
