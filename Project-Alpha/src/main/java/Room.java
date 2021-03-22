import java.util.*;
public class Room {

    private int minRoomNumber;
    private int maxRoomNumber;
    private int roomRate;//is the cost of the particular room
    private Customer[] rooms;

    /**
     * constructor method
     */
    public Room(int minRoomNumber, int maxRoomNumber, int roomRate){
        if (minRoomNumber < 0 || maxRoomNumber< 0|| maxRoomNumber < minRoomNumber)
            throw new IllegalArgumentException("The Room numbers " + minRoomNumber + " and " + maxRoomNumber
                    + " are invalid as they cannot be negative, and must have at least one room.");
        if (roomRate < 0){
            throw new IllegalArgumentException("The room rate: "+ roomRate + " is invalid as it cannot be negative.");
        }
        this.minRoomNumber = minRoomNumber;
        this.maxRoomNumber = maxRoomNumber;
        this.rooms = new Customer[maxRoomNumber -  minRoomNumber + 1];
        this.roomRate = roomRate;
    }

    /**
     * An accessor method that checks whether given room number is valid or not.
     * @param roomNumber of the room
     * @return True if valid else False.
     */
    public boolean isValidRoom(int roomNumber)
    {
        return roomNumber >= minRoomNumber && roomNumber <= minRoomNumber + rooms.length - 1;
    }

    /**
     * An accessor method that returns first room number
     * @return first room's number
     */
    public int getFirstRoom()
    {
        return minRoomNumber;
    }

    /**
     * An accessor method that returns last room number
     * @return last room's number
     */
    public int getLastRoom()
    {
        return maxRoomNumber;
    }

    /**
     * An accessor method that converts a room number to its corresponding array index
     * @param roomNumber of the room
     * @return corresponding array index of room
     */
    public int roomToIndex(int roomNumber)
    {
        if (! isValidRoom(roomNumber))
            throw new IllegalArgumentException("The room number: " + roomNumber
                    + " is not a valid room number for a room.");
        return (roomNumber - minRoomNumber);
    }

    /**
     * An accessor method that converts an array index to a room number
     * @param index of the room
     * @return corresponding room number for the index
     */
    public int indexToRoom(int index)
    {
        if (index < 0 || index >= rooms.length)
            throw new IllegalArgumentException("The room index " + index +
                    " is not a valid index for an array of length " + rooms.length + ".");

        return (index + minRoomNumber);
    }

    /**
     * An accessor method that checks to see if a particular room is occupied
     * @param roomNumber of the room
     * @return true if occupied else false
     */
    public boolean isRoomOccupied(int roomNumber)
    {
        if (! isValidRoom(roomNumber))
            throw new IllegalArgumentException("The room number: " + roomNumber
                    + " is not a valid room number for the room.");
        return  rooms[roomToIndex(roomNumber)] != null;
    }

    /**
     * An accessor method that gets the Customer at a particular room number
     * @param roomNumber of the room
     * @return customer's details in form of Customer class
     */
    public Customer getCustomer(int roomNumber)
    {
        if (! isValidRoom(roomNumber))
        {
            throw new IllegalArgumentException("The room number: " + roomNumber
                    + " is not a valid room number.");
        }

        if (! isRoomOccupied(roomNumber))
        {
            throw new IllegalStateException("Room: " + roomNumber + " is not currently occupied");
        }

        return rooms[roomToIndex(roomNumber)];
    }

    /**
     * A mutator method that assigns a customer to a particular room number
     * @param customer details (Customer class)
     * @param roomNumber of the room
     */
    public void assignCustomerToRoom(Customer customer, int roomNumber)
    {
        if (! isValidRoom(roomNumber))
        {
            throw new IllegalArgumentException("The room number: " + roomNumber
                    + " is not a valid room number.");
        }

        if (isRoomOccupied(roomNumber))
        {
            throw new IllegalStateException("Room: " + roomNumber + " is currently occupied.");
        }

        rooms[roomToIndex(roomNumber)] = customer;
    }

    /**
     * Return a list of the empty rooms' room number of the Motel.
     * @return a list of the empty rooms' room number of the Motel
     */
    public LinkedList<Integer> availableRooms()
    {
        LinkedList<Integer> roomList = new LinkedList<Integer>();
        for (int i = getFirstRoom(); i <= getLastRoom(); i++)
            if (! isRoomOccupied(i))
                roomList.addLast(i);
        return roomList;
    }

    /**
     * gets the room rate
     * @return the room rate
     */
    public int getRoomRate() {
        return roomRate;
    }

    /**
     * sets the room rate
     * @param roomRate of the room that you want to set
     */
    public void setRoomRate(int roomRate) {
        if (roomRate <= 0)
        {
            throw new IllegalArgumentException("The room rate: "+roomRate+
                    " cannot be 0 or less than it.");
        }
        this.roomRate = roomRate;
    }

    /**
     * Remove the Customer from a specific room.
     * @param roomNumber  the label of the room from which the Customer is to be released
     * @precond isValidRoom(roomNumber) && isRoomOccupied(roomNumber)
     */
    public void freeRoom(int roomNumber)
    {
        if (! isValidRoom(roomNumber))
            throw new IllegalArgumentException("The value " + roomNumber
                    + " is not a valid label for a room in the hotel.");

        if (! isRoomOccupied(roomNumber))
            throw new IllegalStateException("Room " + roomNumber + " is not currently occupied"
                    + " so it cannot be freed");
        rooms[roomToIndex(roomNumber)].setBookingStatus("Checked OUT");
        rooms[roomToIndex(roomNumber)].setBookingID(-1);
        rooms[roomToIndex(roomNumber)].setRoomNumber(-1);
        rooms[roomToIndex(roomNumber)] = null;
    }

    /**
     * An accessor method that returns a string representation of all the
     * information about the Rooms in a form suitable for printing including
     * the room rate, room numbers and the customers.
     * @return  -the room number (if the room number is empty) OR
     *          –the room number and the name of the customer living in it.
     */
    public String toString()
    {
        String data = "The Missouri Inn Motel has "
                + rooms.length + " rooms. And it has these customers: ";
        for(int i = 0; i < rooms.length; i++)
        {
            data = data + "\nRoom: " + indexToRoom(i) + " Customer: ";
            if (isRoomOccupied(indexToRoom(i)))
            {
                data = data + rooms[i].toString();
            }
            else
            {
                data = data + "null";
            }
        }
        return data;
    }
}