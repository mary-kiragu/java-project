package com.mary.kiragu.dataaccess;

import com.mary.kiragu.domain.Tenant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class TenantsDataManager {

    static String jdbcurl = "jdbc:mysql://localhost/AMS";
    static String dbUserName = "root";
    static String dbPassword = "brianna";

    /**
     * search through the tenants database table for tenants with the name
     * speficied. Search on all name fields; first name, middle name and surname
     * uses SQL like to compare
     *
     * @param name the name to be search
     * @return
     */
    public List<Tenant> searchTenants(String name) {

        List<Tenant> tenants = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);
            String sqlSelectUser = "SELECT * FROM tenants WHERE FirstName like '" + name + "%' OR SecondName like '" + name
                    + "%' OR surname like '" + name + "%' ORDER BY TenantId DESC";
            PreparedStatement statement = connection.prepareStatement(sqlSelectUser);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Tenant tenant = new Tenant();

                tenant.setId(result.getInt("TenantId"));
                tenant.setFirstName(result.getString("FirstName"));
                tenant.setSecondName(result.getString("SecondName"));
                tenant.setSurname(result.getString("Surname"));
                tenant.setIdNumber(result.getString("IdNumber"));
                tenant.setAmountPaid(result.getInt("AmountPaid"));
                tenant.setBalance(result.getInt("balance"));
                tenant.setEmail(result.getString("Email"));
                tenant.setPhoneNumber(result.getString("PhoneNumber"));
               // tenant.setRoomId(result.getInt("RoomId"));

                tenants.add(tenant);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tenants;
    }

    public Tenant searchTenantsByIdOrIDNumber(String id) {

        Tenant tenant = null;

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);
            String sqlSelectUser = "SELECT * FROM tenants WHERE TenantId = '" + id + "' OR IdNumber = '" + id + "' LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(sqlSelectUser);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                tenant = new Tenant();

                tenant.setId(result.getInt("TenantId"));
                tenant.setFirstName(result.getString("FirstName"));
                tenant.setSecondName(result.getString("SecondName"));
                tenant.setSurname(result.getString("Surname"));
                tenant.setIdNumber(result.getString("IdNumber"));
                tenant.setAmountPaid(result.getInt("AmountPaid"));
                tenant.setBalance(result.getInt("balance"));
                tenant.setEmail(result.getString("Email"));
                tenant.setPhoneNumber(result.getString("PhoneNumber"));
                // tenant.setRoomId(result.getInt("RoomId"));
                

            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tenant;
    }

    public boolean addTenant(Tenant tenant) {
        try {

            //connection
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);

            //sql query
            String sqlInsertTenant = "INSERT INTO tenants (FirstName,SecondName, Surname ,IdNumber,Balance ,AmountPaid, email,PhoneNumber,RoomId ) "
                    + "VALUES(?, ?, ?, ?, ?,?,?,?,? )";

            PreparedStatement statement = connection.prepareStatement(sqlInsertTenant);

            statement.setString(1, tenant.getFirstName());
            statement.setString(2, tenant.getSecondName());
            statement.setString(3, tenant.getSurname());
            statement.setString(4, tenant.getIdNumber());
            statement.setInt(5, tenant.getBalance());
            statement.setInt(6, tenant.getAmountPaid());
            statement.setString(7, tenant.getEmail());
            statement.setString(8, tenant.getPhoneNumber());
            statement.setInt(9, tenant.getRoomId());

            int rows = statement.executeUpdate();
            System.out.println(rows + " user has been added to the db");
            connection.close();
            return true;

        } catch (Exception e) {
            System.out.println("Error on the code");
            System.out.println(e);

            return false;
        }

    }

    public boolean updateTenant(Tenant tenant) throws SQLException {
        //put the logic to update tenant 
        
            //connection
            try{
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);

            //sql query
            String sqlUpdateTenant = "UPDATE TABLE  tenants  set Balance=?where TenantId =? " ;
            
            PreparedStatement statement = connection.prepareStatement(sqlUpdateTenant);
            statement.setInt(1,tenant.getId());
            statement.setInt(2,tenant.getBalance());
            statement.executeUpdate();
            return true;
            }
            
            catch (Exception e) {
            System.out.println("Error on the code");
            System.out.println(e);

            return false;
        }
                  

    }

}
