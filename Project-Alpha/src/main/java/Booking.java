import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Booking {
    /**
     * Treemap for customer data and keyed customer's id proof
     */
    private TreeMap<String, Customer> customers;

    /**
     * Treemap for staff data and keyed staff member's id
     */
    private TreeMap<String, Staff> staffs;

    /**
     * Rooms for the Motel
     */
    private Room rooms;

    /**
     * The instance for the input output interface
     */
    private static InputOutputInterface IO = new IO();

    /**
     * The constructor for booking class. It reads in the data from
     * existing csv database files and initializes the customer and staff
     * treemaps and rooms.
     * @throws IOException
     */
    public Booking() throws IOException {
        customers = new TreeMap<String, Customer>();
        staffs = new TreeMap<String, Staff>();
        CSVReader reader = new CSVReaderBuilder(new FileReader("Customer.csv")).withSkipLines(1).build();
        List<String[]> allCustomerData = reader.readAll();
        for (String[] row : allCustomerData) {

            Customer customer = new Customer(row[0],row[1],row[2]);
            customer.setRoomNumber(Integer.parseInt(row[3]));
            customer.setBookingID(Integer.parseInt(row[4]));
            customer.setBookingStatus(row[5]);
            if (customer.getCheckInDate() != null && customer.getCheckOutDate() != null)
            {
                customer.setCheckInDate(LocalDate.parse(row[6]));
                customer.setCheckOutDate(LocalDate.parse(row[7]));
            }
            customer.setAmountDue(Double.parseDouble(row[8]));
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


        reader = new CSVReaderBuilder(new FileReader("Room.csv")).withSkipLines(1).build();
        List<String[]> allRoomData = reader.readAll();

        int count = 0;
        int firstRoom = 0;
        int lastRoom = 0;
        for (String[] row: allRoomData)
        {
            if (count == 0)
            {
                firstRoom = Integer.parseInt(row[0]);
            }
            if (count == (allRoomData.size() - 1))
            {
                lastRoom = Integer.parseInt(row[0]);
            }
            count++;
        }
        rooms = new Room(firstRoom,lastRoom);
        for (String[] row: allRoomData)
        {
            if (row[1].equals("Y"))
            {
                Customer customeR = customers.get(row[3]);
                customeR.setRoomRate(Double.parseDouble(row[2]));
                rooms.assignCustomerToRoom(customeR,Integer.parseInt(row[0]));
            }
        }
    }

    /**
     * Gets the information for a staff member and then add them
     * to the dictionary of all staff.
     */
    public void addStaff() throws IOException {
        IO.outputString("Adding Staff member to the database...");
        verifyOwnerAndManger();
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
        updateCSV(customers,staffs,rooms);
    }


    /**
     * A method that checks the login details for admin tasks.
     * @param staffs the staff data treemap
     * @param id id for the staff that you are verifying
     */
    public void loginCheck(TreeMap<String,Staff> staffs,String id)
    {
        String userID = IO.readString("Please enter your user ID: ");
        IO.outputString("Entered: "+ userID);
        if (!staffs.get(id).getUserId().equals(userID))
        {
            throw new IllegalStateException("Sorry you entered a wrong user ID. Please, try it again!");
        }
        String pass = IO.readString("Please enter your password: ");
        if (!staffs.get(id).getPassword().equals(pass))
        {
            throw new IllegalStateException("Sorry you entered a wrong password. Please, try it again!");
        }
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
        updateCSV(customers,staffs,rooms);
    }

    /**
     * A method that verifies if the staff member is
     * owner or manager for some admin tasks
     */
    public void verifyOwnerAndManger()
    {
        String id = IO.readString("Please enter your staff id: ");
        IO.outputString("Entered: " + id);
        if (!staffs.containsKey(id)) {
            throw new IllegalStateException("Sorry you are not an existing staff member and cannot have privileges to perform this task.");
        }
        if (!staffs.get(id).getDesignation().equals("Manager") && !staffs.get(id).getDesignation().equals("Owner")) {
            throw new IllegalStateException("Sorry you are not privileged to do this task.");
        }
        try {
            loginCheck(staffs,id);
        }catch (Exception e)
        {
            IO.outputString(e.getMessage());
        }
    }

    /**
     * A method that verifies if the staff member is
     * owner or manager or accountant or receptionist. for some admin tasks
     */
    public void verifyStaff()
    {
        String id = IO.readString("Please enter your staff id: ");
        IO.outputString("Entered: " + id);
        if (!staffs.containsKey(id)) {
            throw new IllegalStateException("Sorry you are not an existing staff member and cannot have privileges to perform this task.");
        }
        if (!staffs.get(id).getDesignation().equals("Manager") && !staffs.get(id).getDesignation().equals("Owner")
            && !staffs.get(id).getDesignation().equals("Receptionist") && !staffs.get(id).getDesignation().equals("Accountant")) {
            throw new IllegalStateException("Sorry you are not privileged to do this task.");
        }
        try {
            loginCheck(staffs,id);
        }catch (Exception e)
        {
            IO.outputString(e.getMessage());
        }
    }

    /**
     * A method to remove customer form the database.
     */
    public void removeCustomer()
    {
        IO.outputString("Removing Customer from the database...");
        verifyOwnerAndManger();
        String id = IO.readString("Enter the id proof number of the Customer: ");
        IO.outputString("Entered: " + id);
        if (!customers.containsKey(id)) {
            throw new IllegalStateException("Customer with the id: " + id + " not found in database.");
        }
        customers.remove(id);
    }

    /**
     * A method to remove staff member from the database.
     */
    public void removeStaff()
    {
        IO.outputString("Removing Staff member from the database...");
        verifyOwnerAndManger();
        String id = IO.readString("Enter the Staff ID of the staff member: ");
        IO.outputString("Entered: " + id);
        if (!staffs.containsKey(id)) {
            throw new IllegalStateException("Staff member with the staff id: " + id + " not found in database.");
        }
        staffs.remove(id);
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

        LocalDate todaysDate = LocalDate.now();
        customer.setCheckInDate(todaysDate);

        int stay = IO.readInt("Please enter the number of nights of stay: ");
        IO.outputString("Entered: "+ stay);
        if (stay <= 0)
        {
            throw new IllegalArgumentException("The entered stay: "+stay+" must be at least of 1 day.");
        }
        todaysDate = todaysDate.plusDays(stay);
        customer.setCheckOutDate(todaysDate);

        int roomRate = IO.readInt("Please enter room rate for 1 night for this booking: ");
        IO.outputString("Entered: "+ roomRate);
        if (roomRate <= 0)
        {
            throw new IllegalArgumentException("The entered room rate: "+roomRate+" cannot be 0 or less than it.");
        }
        customer.setRoomRate(roomRate);
        int promotion = IO.readInt("Do you want to add promo? If yes enter amount else, enter 0.");
        customer.setAmountDue((stay*roomRate)-promotion);

        int bookingID = IO.readInt("Enter the booking ID for the customer: ");
        IO.outputString("Entered: " + bookingID);

        if (bookingID <= 0) {
            throw new IllegalArgumentException(" The booking ID: " + bookingID + " cannot be less than equal to zero.");
        }

        rooms.assignCustomerToRoom(customer, roomNumber);
        customer.setRoomNumber(roomNumber);
        customer.setBookingID(bookingID);
        customer.setBookingStatus("Reserved");
        updateCSV(customers,staffs,rooms);
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
        writeInvoice(customer);
        IO.outputString("Did the customer paid the amount due?");
        String[] yesNo = new String[]{"Yes","No"};
        int choice = IO.readChoice(yesNo);
        if (choice == 0)
        {
            customer.setAmountDue(0.0);
        }
        rooms.freeRoom(roomNumber);
        updateCSV(customers,staffs,rooms);
    }

    /**
     * A method that writes the generated invoice to the database
     * as a record of sales.
     * @param customer
     */
    public void writeInvoice(Customer customer) throws IOException {
        CSVReader reader = new CSVReaderBuilder(new FileReader("Invoices.csv")).withSkipLines(0).build();
        List<String[]> allInvoiceData = reader.readAll();
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter("Invoices.csv"));

        for (String[] row : allInvoiceData)
        {
            writer.writeNext(row);
        }
        String[] entry = new String[4];
        entry[0] = customer.getIdProof();
        entry[1] = customer.getName();
        entry[2] = printInvoice(customer);
        entry[3] = String.valueOf(customer.getBookingID());
        writer.writeNext(entry);
        writer.close();
    }

    /**
     *  A method to display invoices from the database from the booking ID.
     * @throws IOException
     */
    public void displayInvoices() throws IOException {
        verifyStaff();
        IO.outputString("Please select the Booking ID for which you want to see the invoice");
        CSVReader reader = new CSVReaderBuilder(new FileReader("Invoices.csv")).withSkipLines(1).build();
        List<String[]> allInvoiceData = reader.readAll();
        reader.close();
        String[] listOfBookingID = new String[allInvoiceData.size()];
        int count = 0;
        for (String[] row : allInvoiceData)
        {
            listOfBookingID[count] = row[3];
            count++;
        }

        int index = IO.readChoice(listOfBookingID);
        count = 0;
        for (String[] row : allInvoiceData)
        {
            if (count == index)
            {
                IO.outputString(row[2]);
            }
            count++;
        }
    }

    /**
     * A method that prints the invoices for the customer that are checking out.
     * @param customer the customer that  is checking out.
     * @return
     */
    public String printInvoice(Customer customer) {
        String bill = "\n";
        bill += "Date: ";
        bill += customer.getCheckOutDate().toString() + "\n";
        bill += customer.toString();
        bill += "\n Total amount due: $" + customer.getAmountDue() + "\n";
        return bill;
    }

    /**
     * A mutator method that writes to database CSV files, the updated TreeMaps.
     * @param customerS an updated version of customer TreeMap.
     * @param staffS an updated version of staff TreeMap.
     * @param rooms  updated rooms
     * @throws IOException when the CSV files are not accessible or not readable.
     */
    public void updateCSV(TreeMap<String,Customer> customerS,TreeMap<String,Staff> staffS,Room rooms) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("Customer.csv"));
        String[] header1 = { "Name", "Contact Number", "ID Proof","Room Number","Booking ID","Booking Status" ,"Check IN Date", "Check OUT Date","Amount Due"};
        writer.writeNext(header1);
        for (String key : customerS.keySet())
        {
            String[] entry = new String[9];
            entry[0] = customerS.get(key).getName();
            entry[1] = customerS.get(key).getContactNumber();
            entry[2] = key;
            entry[3] = String.valueOf(customerS.get(key).getRoomNumber());
            entry[4] = String.valueOf(customerS.get(key).getBookingID());
            entry[5] = customerS.get(key).getBookingStatus();
            entry[6] = String.valueOf(customerS.get(key).getCheckInDate());
            entry[7] = String.valueOf(customerS.get(key).getCheckOutDate());
            entry[8] = String.valueOf(customerS.get(key).getAmountDue());
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

        writer = new CSVWriter(new FileWriter("Room.csv"));
        String[] header3 = { "Room Number","Is Occupied", "Room Rate", "Customer ID","Customer Details"};
        writer.writeNext(header3);
        for (int roomNumber : rooms.allRooms())
        {
            String[] entry = new String[5];
            entry[0] = String.valueOf(roomNumber);
            if (rooms.isRoomOccupied(roomNumber))
            {
                entry[1] = "Y";
                entry[2] = String.valueOf(rooms.getCustomer(roomNumber).getRoomRate());
                entry[3] = rooms.getCustomer(roomNumber).getIdProof();
                entry[4] = rooms.getCustomer(roomNumber).toString();
            }
            else
            {
                entry[1] = "N";
                entry[2] = null;
                entry[3] = null;
                entry[4] = null;
            }
            writer.writeNext(entry);
        }
        writer.close();
    }

    /**
     * A method that display summary of the Motel management system
     * A report of current state in management system.
     */
    public void summary() {
        IO.outputString(this.toString());
    }


    /**
     * A method that returns a string representation of all the information about
     * the Motel Management System in a form suitable for printing.
     * For instance it might include information about the rooms,
     * a list of customers and a list of staff members
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

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int task = -1;
        Booking managementSystem;
        IO.outputString("Initializing the Motel Management system...");
        IO.outputString(">>>>>>>> Welcome to Motel Management System <<<<<<<<");
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
                String[] optionSList = new String[]{"1: Quit", "2: Add a new customer", "3: Add a new staff member",
                        "4: Check IN", "5: Check OUT","6. Remove Customer","7. Remove Staff member","8. Get Invoice" ,
                        "9: Display Summary"};
                task = IO.readChoice(optionSList);
                task += 1;
                if (task == 1) managementSystem.summary();
                else if (task == 2) managementSystem.addCustomer();
                else if (task == 3) managementSystem.addStaff();
                else if (task == 4) managementSystem.checkIN();
                else if (task == 5) managementSystem.checkOUT();
                else if (task == 6) managementSystem.removeCustomer();
                else if (task == 7) managementSystem.removeStaff();
                else if (task == 8) managementSystem.displayInvoices();
                else if (task == 9) managementSystem.summary();
                else IO.outputString("Invalid option, try again.");
            } catch (RuntimeException | IOException e) {
                IO.outputString(e.getMessage());
                managementSystem.updateCSV(managementSystem.customers, managementSystem.staffs, managementSystem.rooms);
            }
        }
    }
}