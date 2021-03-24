import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class Checker {

    private Room rooms;
    private static InputOutputInterface IO = new IO();

    public Checker()
    {
        IO.outputString(">>>>>>> Welcome to the Motel Management System <<<<<<<");
        int firstRoom = IO.readInt("Please enter the first room number: ");
        IO.outputString("Entered: " + firstRoom);
        int lastRoom = IO.readInt("Please enter the last room number: ");
        IO.outputString("Entered: " + lastRoom);
        rooms = new Room(firstRoom, lastRoom);
    }

    public void writeRooms(Room rooms) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("Room.csv"));
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

    public static void main(String[] args) throws IOException {
        Checker checker = new Checker();
        checker.writeRooms(checker.rooms);
    }
}
