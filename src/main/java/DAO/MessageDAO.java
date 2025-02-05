package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    public Message insertMessage(Message message){
        int message_id = 0;
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.executeUpdate();
            ResultSet pkrs = ps.getGeneratedKeys();
            pkrs.next();
            message_id = pkrs.getInt("message_id");
            message.setMessage_id(message_id);

            return message;
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Message> getAllMessages(){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch"));
                    messages.add(message);
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message getMessageById(int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT message_text FROM message WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch"));
                    return message;
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getMessageById(message_id);
    }
    //+
    public Message deleteMessageById(int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE message_text FROM message WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message_id);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0){
                return getMessageById(message_id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getMessageById(message_id);
    }
    //+
    public Message updateMessageById(Message message, int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE message_text SET message_text = ? WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, message.getMessage_text());
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0){
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getMessageById(message_id);
    }

    public List<Message> getAllMessagesByUser(int posted_by){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> userMessages = new ArrayList<>();
        try {
            String sql = "SELECT message_text FROM message WHERE posted_by = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, posted_by);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch"));
                    userMessages.add(message);
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userMessages;
    }
}