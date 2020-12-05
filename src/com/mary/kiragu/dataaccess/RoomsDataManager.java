package com.mary.kiragu.dataaccess;

import com.mary.kiragu.domain.Room;
//mport com.mary.kiragu.dataaccess.RoomsDataManager;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class RoomsDataManager {

    static String jdbcurl = "jdbc:mysql://localhost/AMS";
    static String dbUserName = "root";
    static String dbPassword = "brianna";
    int roomId;
    String roomname;
    
    
    int rent;
     public List<Room> searchRooms(String name) {

        List<Room> rooms = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);
            String sqlSelectRoom = "SELECT * FROM rooms WHERE RoomName like '" + name + "%'  ORDER BY RoomId DESC";
            PreparedStatement statement = connection.prepareStatement(sqlSelectRoom);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Room room = new Room();
                room.setRoomId(result.getInt("RoomID"));
                room.setRoomName(result.getString("RoomName"));
                
                room.setRent(result.getInt("Rent"));
                
                rooms.add(room);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return rooms;
    }


    public boolean addRoom(Room room) {
        //put the logic to insert 

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);

            String sqlInsertRoom = "INSERT INTO rooms ( RoomName , Rent   ) "
                    + "VALUES( ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sqlInsertRoom);

            statement.setString(1, room.getRoomName());
            
            statement.setInt(2, room.getRent());

            System.out.println("query:" + statement.toString());

            int rows = statement.executeUpdate();
            System.out.println(rows + " room has been added to the db");

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void updateRoom(Room room) {
        //put the logic to update tenant 
    }

}
