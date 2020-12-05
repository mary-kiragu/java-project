package com.mary.kiragu.dataaccess;

import com.mary.kiragu.domain.AccountStatement;
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
public class AccountStatementDataManager {

    static String jdbcurl = "jdbc:mysql://localhost/AMS";
    static String dbUserName = "root";
    static String dbPassword = "brianna";

    public boolean accountStatement(AccountStatement accountStatement) {

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);

            String sqlInsertAccountStatement = "INSERT INTO accountstatements (TransactionDate,TransactionType,Amount,Details,User,BalanceBefore,BalanceAfter,TenantId) "
                    + "VALUES(NOW(),?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sqlInsertAccountStatement);

            System.out.println("query: " + statement);

            statement.setString(1, accountStatement.getTransactionType());
            statement.setInt(2, accountStatement.getAmount());
            statement.setString(3, accountStatement.getDetails());
            statement.setString(4, accountStatement.getUser());
            statement.setInt(5, accountStatement.getBalanceBefore());
            statement.setInt(6, accountStatement.getBalanceAfter());
            statement.setInt(7, accountStatement.getTenantId());
            System.out.println("query:" + statement.toString());

            int rows = statement.executeUpdate();
            System.out.println(rows + " account statement has been added to the db");

            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<AccountStatement> searchByTenantId(String tenantId) {

        List<AccountStatement> accountStatements = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcurl, dbUserName, dbPassword);
            String sqlSelectUser = "SELECT * FROM AccountStatements WHERE TenantId = ? ORDER BY StatementId DESC";
            PreparedStatement statement = connection.prepareStatement(sqlSelectUser);
            statement.setString(1, tenantId);

            System.out.println("query: " + statement);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                AccountStatement accountStatement = new AccountStatement();
                accountStatement.setStatementId(result.getInt("StatementId"));
                accountStatement.setTransactionDate(result.getString("TransactionDate"));
                accountStatement.setTransactionType(result.getString("TransactionType"));
                accountStatement.setAmount(result.getInt("Amount"));
                accountStatement.setDetails(result.getString("Details"));
                accountStatement.setUser(result.getString("User"));
                accountStatement.setBalanceBefore(result.getInt("BalanceBefore"));
                accountStatement.setBalanceAfter(result.getInt("BalanceAfter"));
                accountStatement.setTenantId(result.getInt("TenantId"));

                accountStatements.add(accountStatement);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return accountStatements;
    }

}
