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

    public Message addMessage(String message_text, Message message, int posted_by){
        //check message model and readme.md ##3 for requirements
        //&& messageDAO.insertMessage(Message message)
        if(message_text.length() > 0 && message_text.length() < 256 && message.getPosted_by() == posted_by){
            return messageDAO.insertMessage(addMessage(message_text, message, posted_by)); //fix this 1
        }
            return null;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }
    //~+
    public Message deleteMessageById(int message_id){
        return messageDAO.deleteMessageById(message_id);
    }
    //~+
    public Message updateMessageById(Message message, int message_id){
        //check message model and readme.md ##7 for requirements
        if(message.message_text.length() > 0 && 
           message.message_text.length() <= 255 && 
           message.getMessage_id() == message_id
        ){
            Message updatedMessage = messageDAO.insertMessage(message);// then fix this 2
            return updatedMessage;
        }
            return null;
    }

    public List<Message> getAllMessagesByUser(Message message, int posted_by) {

        return messageDAO.getAllMessagesByUser(posted_by);
    }
}
