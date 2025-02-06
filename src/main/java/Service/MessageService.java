package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        //fix this 1//check message model and readme.md ##3 for requirements//&& messageDAO.insertMessage(Message message)
         
        if(message.message_text.length() > 0 && 
           message.message_text.length() < 256 && 
           accountDAO.getAccountId(message.posted_by) != null
        ){
            Message messageFeed = messageDAO.insertMessage(message);
            return messageFeed;
        }
            return null;
    }

    public List<Message> getAllMessages() {
        List<Message> allMessages = messageDAO.getAllMessages();
        return allMessages;
    }

    public Message getMessageById(int message_id){
        Message getMessage = messageDAO.getMessageById(message_id);
        return getMessage;
    }
    //~+
    public Message deleteMessageById(int message_id){
        Message deletedMessage = messageDAO.getMessageById(message_id);
        if(deletedMessage != null){
            messageDAO.deleteMessageById(message_id);
            return deletedMessage;
        }
            return null;
    }
    //~+
    public Message updateMessageById(int message_id, Message message){
        //check message model and readme.md ##7 for requirements
        // then fix this 2
        if(message.message_text.length() > 0 && 
           message.message_text.length() <= 255 && 
           messageDAO.getMessageById(message_id) != null
        ){
            messageDAO.updateMessageById(message_id, message);
            return messageDAO.getMessageById(message_id);
        }
            return null;
    }

    public List<Message> getAllMessagesByUser(int posted_by) {
        List <Message> allMessagesOfUser = messageDAO.getAllMessagesByUser(posted_by);
        return allMessagesOfUser;
    }
}
