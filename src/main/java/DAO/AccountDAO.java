package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {
    
    /**
     * 
     * @param account
     * @return Account with matching username and password
     */
    public Account getAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "SELECT * FROM Account WHERE username=? AND password=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
            }
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }


    /**
     * 
     * @param id
     * @return blank password Account with matching id
     */
    public Account getAccountById(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "SELECT * FROM Account WHERE account_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("username"), "");
            }
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }


    /**
     * 
     * @param username
     * @return blank password Account with matching username
     */
    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "SELECT * FROM Account WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("username"), "");
            }
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }


    /**
     * 
     * @param account
     * @return newly added Account
     */
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "INSERT INTO Account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if (pkeyResultSet.next()) {
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    
}
