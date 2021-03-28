import java.util.*;

public class testRoom
{
    public static void main(String[] args) {
        int numErrors = 0;

        Room R1 = new Room(100, 106);
        if (R1.getFirstRoom() != 100) {
            System.out.println("Constructor failed: The room has number " + R1.getFirstRoom() + " rather than 100");
            numErrors++;
        }
        if (R1.getLastRoom() != 106) {
            System.out.println("Constructor failed: The room has number " + R1.getLastRoom() + " rather than 106");
            numErrors++;
        }

        if (!R1.isValidRoom(103)) {
            System.out.println("isValidRoom() failed: The room number 103 is not valid instead it should be valid");
            numErrors++;
        }

        LinkedList<Integer> expectedAllRooms = new LinkedList<>();
        expectedAllRooms.add(100);
        expectedAllRooms.add(101);
        expectedAllRooms.add(102);
        expectedAllRooms.add(103);
        expectedAllRooms.add(104);
        expectedAllRooms.add(105);
        expectedAllRooms.add(106);
        if(!R1.allRooms().equals(expectedAllRooms)) {
            System.out.println("allRooms() failed: The room number list should be " +
                    "[100, 101, 102, 103, 104, 105, 106] instead of " + R1.allRooms());
            numErrors++;
        }

        Customer C1 = new Customer("Sachin", "306-306-3061", "ABC123");
        R1.assignCustomerToRoom(C1,105);
        if (!R1.isRoomOccupied(105)) {
            System.out.println("isRoomOccupied() or assignCustomerToRoom() failed: " +
                    "The room number 105 should have been occupied");
            numErrors++;
        }

        if (R1.getCustomer(105) != C1) {
            System.out.println("getCustomer() failed: The Customer assigned to room number 105 is " +
                    R1.getCustomer(105) + " rather than Sachin");
            numErrors++;
        }


        String expected1 = "The Missouri Inn Motel has 7 rooms. And it has these customers: \n" +
                "Room: 100 Customer: null\n" +
                "Room: 101 Customer: null\n" +
                "Room: 102 Customer: null\n" +
                "Room: 103 Customer: null\n" +
                "Room: 104 Customer: null\n" +
                "Room: 105 Customer: \nName: Sachin\n" +
                "Contact number: 306-306-3061\n" +
                "ID Proof: ABC123\n" +
                "Room Number:-1\n" +
                "Booking ID:-1\n" +
                "Booking Status:null\n" +
                "Room: 106 Customer: null";

        if (!R1.toString().equals(expected1)) {
            System.out.println("toString() failed: Expected: " + expected1 + " but returned " + R1);
            numErrors++;
        }

        Customer C2 = new Customer("Rohit", "306-306-9999", "XYZ123");
        R1.assignCustomerToRoom(C2,102);
        R1.freeRoom(105);

        expected1 = "The Missouri Inn Motel has 7 rooms. And it has these customers: \n" +
                "Room: 100 Customer: null\n" +
                "Room: 101 Customer: null\n" +
                "Room: 102 Customer: \nName: Rohit\n" +
                "Contact number: 306-306-9999\n" +
                "ID Proof: XYZ123\n" +
                "Room Number:-1\n" +
                "Booking ID:-1\n" +
                "Booking Status:null\n" +
                "Room: 103 Customer: null\n" +
                "Room: 104 Customer: null\n" +
                "Room: 105 Customer: null\n" +
                "Room: 106 Customer: null";

        if (!R1.toString().equals(expected1)) {
            System.out.println("toString() or freeRoom() failed: Expected: " + expected1 + " but returned " + R1);
            numErrors++;
        }

        LinkedList<Integer> expectedAvailableRooms = new LinkedList<Integer>();
        expectedAvailableRooms.add(100);
        expectedAvailableRooms.add(101);
        expectedAvailableRooms.add(103);
        expectedAvailableRooms.add(104);
        expectedAvailableRooms.add(105);
        expectedAvailableRooms.add(106);
        if (!R1.availableRooms().equals(expectedAvailableRooms)) {
            System.out.println("toString() or freeRoom() failed: Expected: " + expected1 + " but returned " + R1);
            numErrors++;
        }

        System.out.println("\nThe number of errors found is " + numErrors);
    }
}
