import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Booking {
    private TreeMap<String, Customer> customers;

    private TreeMap<String, Staff> staffs;

    private Room rooms;

    private static InputOutputInterface IO = new IO();

    private long checkInDateTime;
    private long checkOutDateTime;

    public Booking() throws IOException {
        customers = new TreeMap<String, Customer>();
        staffs = new TreeMap<String, Staff>();
        CSVReader reader = new CSVReaderBuilder(new FileReader("Customer.csv")).withSkipLines(1).build();
        List<String[]> allCustomerData = reader.readAll();
        for (String[] row : allCustomerData) {

            Customer customer = new Customer(row[0],row[1],row[2]);
            customer.setRoomNumber(Integer.parseInt(row[3]));
            customer.setBookingID(Integer.parseInt(row[4]));
            customer.setBookingStatus(row[3]);
            customers.put(customer.getIdProof(), customer);
        }
        reader.close();
        reader = new CSVReaderBuilder(new FileReader("Staff.csv")).withSkipLines(1).build();
        List<String[]> allStaffData = reader.readAll();
        for (String[] row : allStaffData) {
            Staff staff = new Staff(row[0],row[1],row[2],row[3],row[4]);
            staffs.put(staff.getStaffId(), staff);
        }
        reader.close();

        IO.outputString(">>>>>>> Welcome to the Motel Management System <<<<<<<");
        int firstRoom = IO.readInt("Please enter the first room number: ");
        IO.outputString("Entered: " + firstRoom);
        int lastRoom = IO.readInt("Please enter the last room number: ");
        IO.outputString("Entered: " + lastRoom);
        int roomRate = IO.readInt("Please enter the room rate: ");
        IO.outputString("Entered: " + roomRate);
        rooms = new Room(firstRoom, lastRoom, roomRate);
    }

    /**
     * Gets the information for a staff member and then add them
     * to the dictionary of all staff.
     */
    public void addStaff() throws IOException {
        IO.outputString("Adding Staff member to the database...");
        String id = IO.readString("Please enter your id: ");
        IO.outputString("Entered: " + id);
        if (!staffs.containsKey(id)) {
            throw new IllegalStateException("Sorry you are not an existing staff member and cannot have privileges to add any new member.");
        }
        if (!staffs.get(id).getDesignation().equals("Manager") && !staffs.get(id).getDesignation().equals("Owner")) {
            throw new IllegalStateException("Sorry you are not privileged to add any new staff member.");
        }
        String name = IO.readString("Enter the name of the new staff member: ");
        IO.outputString("Entered: " + name);
        String staffID = IO.readString("Enter the staff id for new staff member: ");
        IO.outputString("Entered: " + staffID);
        String designation = IO.readString("Enter the designation of new staff member: ");
        IO.outputString("Entered: " + designation);

        if (staffs.containsKey(staffID)) {
            throw new IllegalStateException("Staff member cannot be added as there is already staff member with that staff id: " + staffID);
        }

        Staff staff = new Staff(name, staffID, designation, null, null);
        staffs.put(staffID, staff);
        updateCSV(customers,staffs);
    }

    /**
     * Gets the information for a new customer and then add the customer
     * to the dictionary of all customers.
     */
    public void addCustomer() throws IOException {
        IO.outputString("Adding Customer to the database...");
        String name = IO.readString("Enter the name of the Customer: ");
        IO.outputString("Entered: " + name);
        String contactNumber = IO.readString("Enter the contact number of the Customer: ");
        IO.outputString("Entered: " + contactNumber);
        String id = IO.readString("Enter the id proof number of the Customer: ");
        IO.outputString("Entered: " + id);
        if (customers.containsKey(id)) {
            throw new IllegalStateException("Customer with the id: " + id + " already exists.");
        }

        Customer customer = new Customer(name, contactNumber, id);
        customers.put(id, customer);
        updateCSV(customers,staffs);
    }

    /**
     * The check in method that assigns room to the customer
     */
    public void checkIN() throws IOException {
        IO.outputString("Assigning Room to a customer...");
        String id = IO.readString("Enter the id proof of the customer: ");

        Customer customer = customers.get(id);
        if (customer == null) {
            IO.outputString("There is no customer with id: " + id
                    + " Please enter the customer to database first.");
            return;
        }

        if (customer.getRoomNumber() != -1)
            throw new IllegalStateException(" Customer: " + customer.getName()
                    + " has already been assigned a room: " + customer.getRoomNumber() + " .");


        int roomNumber = IO.readInt("Enter the room number for the customer: ");
        IO.outputString("Entered: " + roomNumber);

        if (roomNumber < rooms.getFirstRoom() || roomNumber > rooms.getLastRoom())
            throw new IllegalArgumentException("Room number " + roomNumber + " is not valid, as "
                    + "the value must be between " + rooms.getFirstRoom()
                    + " and " + rooms.getLastRoom());

        int bookingID = IO.readInt("Enter the booking ID for the customer: ");
        IO.outputString("Entered: " + bookingID);

        if (bookingID <= 0) {
            throw new IllegalArgumentException(" The booking ID: " + bookingID + " cannot be less than equal to zero.");
        }

        rooms.assignCustomerToRoom(customer, roomNumber);
        customer.setRoomNumber(roomNumber);
        customer.setBookingID(bookingID);
        customer.setBookingStatus("Reserved");
        updateCSV(customers,staffs);
    }

    /**
     * The checkOUT method that checks out the customer.
     */
    public void checkOUT() throws IOException {
        IO.outputString("Checking OUT Customer...");
        String id = IO.readString("Enter the id proof of the customer: ");

        Customer customer = customers.get(id);
        if (customer == null) {
            throw new IllegalStateException("There is no customer with id: " + id
                    + " Please enter the customer to database first.");
        }

        if (customer.getRoomNumber() == -1)
            throw new IllegalStateException(" Customer: " + customer.getName()
                    + " has not been assigned a room.");


        int roomNumber = customer.getRoomNumber();

        if (!rooms.isValidRoom(roomNumber)) {
            throw new IllegalStateException("The room number: " + roomNumber + " is invalid.");
        }

        customer.setBookingStatus("Checking OUT");
        IO.outputString(printInvoice(customer));
        rooms.freeRoom(roomNumber);
        updateCSV(customers,staffs);
    }

    public String printInvoice(Customer customer) {
        String bill = "\n";
        bill += customer.toString();
        bill += "\n Total amount due: $" + rooms.getRoomRate() + "\n";
        return bill;
    }

    public long getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(long checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public long getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(long checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public void updateCSV(TreeMap<String,Customer> customerS,TreeMap<String,Staff> staffS) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("Customer.csv"));
        String[] header1 = { "Name", "Contact Number", "ID Proof","Room Number","Booking ID","Booking Status" };
        writer.writeNext(header1);
        for (String key : customerS.keySet())
        {
            String[] entry = new String[6];
            entry[0] = customerS.get(key).getName();
            entry[1] = customerS.get(key).getContactNumber();
            entry[2] = key;
            entry[3] = String.valueOf(customerS.get(key).getRoomNumber());
            entry[4] = String.valueOf(customerS.get(key).getBookingID());
            entry[5] = customerS.get(key).getBookingStatus();
            writer.writeNext(entry);
        }
        writer.close();

        writer = new CSVWriter(new FileWriter("Staff.csv"));
        String[] header2 = { "Name", "Staff ID", "Designation","User ID","Password"};
        writer.writeNext(header2);
        for (String key : staffS.keySet())
        {
            String[] entry = new String[5];
            entry[0] = staffS.get(key).getName();
            entry[1] = key;
            entry[2] = staffS.get(key).getDesignation();
            entry[3] = staffS.get(key).getUserId();
            entry[4] = staffS.get(key).getPassword();
            writer.writeNext(entry);
        }
        writer.close();
    }

    public void summary() {
        IO.outputString(this.toString());
    }


    /**
     * A method that returns a string representation of all the information about
     * the Motel Management System in a form suitable for printing.
     * For instance it might include information about the rooms,
     * a list of customers and a list of staff members
     *
     * @return details about rooms, customers and staffs in the system.
     */
    public String toString() {
        String data = "------------------------- The Motel Management System -------------------------";
        data = data + "\n----- Motel: Missouri Inn -------";
        data = data + rooms.toString();
        data = data + "\n-> There are " + customers.size() + " customers in the database";
        data = data + "\n-> And they are as follows: ";
        Set<Map.Entry<String, Customer>> customer = customers.entrySet();
        int i = 1;
        for (Map.Entry<String, Customer> x : customer) {
            data = data + "\n" + i + ". " + x.getValue().toString();
            i++;
        }
        data = data + "\n\n-> There are " + staffs.size() + " staff members in the database";
        data = data + "\n-> And they are as follows: ";
        Set<Map.Entry<String, Staff>> staff = staffs.entrySet();
        int j = 1;
        for (Map.Entry<String, Staff> x : staff) {
            data = data + "\n" + j + ". " + x.getValue().toString();
            j++;
        }
        data = data + "\n" + "-----------------------------------------------------------------------";
        return data;
    }

    public static void main(String[] args) {
        int task = -1;
        Booking managementSystem;
        IO.outputString("Initializing the Motel Management system...");

        while (true) {
            // keep prompting until the user enters the data correctly
            try {
                managementSystem = new Booking();
                break;
            } catch (RuntimeException | IOException e) {
                IO.outputString(e.getMessage());
            }
        }

        while (task != 1) {
            try {
                String[] optionSList = new String[]{"1: Quit", "2: add a new customer", "3: add a new staff member", "4: Check IN", "5: Check OUT", "6: Display Summary"};
                task = IO.readChoice(optionSList);
                task += 1;
                if (task == 1) managementSystem.summary();
                else if (task == 2) managementSystem.addCustomer();
                else if (task == 3) managementSystem.addStaff();
                else if (task == 4) managementSystem.checkIN();
                else if (task == 5) managementSystem.checkOUT();
                else if (task == 6) managementSystem.summary();
                else IO.outputString("Invalid option, try again.");
            } catch (RuntimeException | IOException e) {
                IO.outputString(e.getMessage());
            }
        }
    }
}