package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    
    public boolean accountLogin(String username){
        Connection conn = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT username FROM Account WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Account addUser(Account account){
        Connection conn = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO Account (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0){
                ResultSet pkrs = ps.getGeneratedKeys();
                if(pkrs.next()){
                    int generated_account_id = (int) pkrs.getLong(1);
                    return new Account(generated_account_id, account.getUsername(), account.getPassword());
                }
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
