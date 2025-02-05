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
        //check message model and readme.md ##3 for requirements//&& messageDAO.insertMessage(Message message)
        Message messageFeed = messageDAO.insertMessage(addMessage(message_text, message, posted_by)); //fix this 1
        if(message_text.length() > 0 && 
           message_text.length() < 256 && 
           message.getPosted_by() == posted_by
        ){
            if(messageFeed != null){
              return messageFeed;
            }
        }
            return null;
    }

    public List<Message> getAllMessages() {
        List<Message> allMessages = messageDAO.getAllMessages();
        if(allMessages != null){
            return allMessages;
        }
            return null;
    }

    public Message getMessageById(int message_id){
        Message getMessage = messageDAO.getMessageById(message_id);
        if(getMessage != null){
            return getMessage;
        }
            return null;
    }
    //~+
    public Message deleteMessageById(int message_id){
        Message deletedMessage = messageDAO.deleteMessageById(message_id);
        if(deletedMessage != null){
            return deletedMessage;
        }
            return null;
    }
    //~+
    public Message updateMessageById(Message message, int message_id, int posted_by){
        //check message model and readme.md ##7 for requirements
        Message updatedMessage = messageDAO.updateMessageById(message, message_id);// then fix this 2
        if(message.message_text.length() > 0 && 
           message.message_text.length() <= 255 && 
           message.getPosted_by() == posted_by
        ){
            if(updatedMessage != null){
                return updatedMessage;
            }
        }
            return null;
    }

    public List<Message> getAllMessagesByUser(Message message, int posted_by) {
        List <Message> allMessagesOfUser = messageDAO.getAllMessagesByUser(posted_by);
        if(allMessagesOfUser != null){
            return allMessagesOfUser;
        }
        return null;
    }
}
