package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(String message_text){
        //check message model and readme.md ##3 for requirements
        if(message_text != null && message_text.length() < 256){
            return messageDAO.insertMessage(null);
        }
            return null;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(Message message, int message_id){
        return messageDAO.deleteMessageById(message_id);
    }

    public Message updateMessageById(Message message, int message_id){
        //check message model and readme.md ##7 for requirements
        if(message.message_text != null && message.message_text.length() < 256){
            return messageDAO.updateMessageById(message, message_id);
        }
            return null;
    }

    public List<Message> getAllMessagesByUser(Message message, int posted_by) {
        return messageDAO.getAllMessagesByUser(posted_by);
    }
}
