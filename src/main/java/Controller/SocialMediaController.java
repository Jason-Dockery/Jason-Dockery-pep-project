package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    private MessageService messageService;
    private AccountService accountService;

    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }

    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::postAccountRegistrationHandler);
        app.post("/login", this::postAccountLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }
           
    private void postAccountRegistrationHandler(Context context) throws JsonProcessingException{
        try{
            ObjectMapper om = new ObjectMapper();
            Account account = om.readValue(context.body(), Account.class);
            Account addedUserAccount = accountService.addUser(account);
            if(addedUserAccount != null){
                context.json(addedUserAccount);
                context.status(200);
            } else {
                context.status(400);
            }
        }catch(Exception e){
            context.status(500);
        }
    }
           
    private void postAccountLoginHandler(Context context) throws JsonProcessingException{
        try{
            ObjectMapper om = new ObjectMapper();
            Account account = om.readValue(context.body(), Account.class);
            Account userAccount = accountService.accountLogin(account);
            if(userAccount != null){
                context.json(userAccount);
                context.status(200);
            } else {
                context.status(401);
            }
        }catch(Exception e){
            context.status(500);
        }
    
    }    

    private void postMessageHandler(Context context) throws JsonProcessingException{
        try{
            ObjectMapper om = new ObjectMapper();
            Message message = om.readValue(context.body(), Message.class);
            Message messageFeed = messageService.addMessage(message); //consider message.message_text
            if(messageFeed != null){
                context.json(messageFeed);
                context.status(200);
            } else {
                context.status(400);
            }
        }catch(Exception e){
            context.status(500);
        }
    }

    private void getAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
        context.status(200);
        }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException{
        // ObjectMapper om = new ObjectMapper();
        // Message message = om.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message getMessage = messageService.getMessageById(message_id);//consider just message or message, message_id
        if(getMessage != null){
            context.json(getMessage);
            context.status(200);
        } else {
            context.status(200);
        }}

    //+
    private void deleteMessageByIdHandler(Context context) throws JsonProcessingException{
        try{
            int message_id = Integer.parseInt(context.pathParam("message_id"));
            Message deletedMessage = messageService.deleteMessageById(message_id); //consider just message or message, message_id
            if(deletedMessage != null){
                context.json(deletedMessage);
                context.status(200);
            } else {
                context.status(200);
            }
        }catch(Exception e){
            context.status(500);
        }
    }

    private void updateMessageByIdHandler(Context context) throws JsonProcessingException{
        try{
            ObjectMapper om = new ObjectMapper();
            Message message = om.readValue(context.body(), Message.class);
            int message_id = Integer.parseInt(context.pathParam("message_id"));
            //int posted_by = Integer.parseInt(context.pathParam("message_id"));
            Message updatedMessage = messageService.updateMessageById(message_id, message); //consider just message or message, message_id
            if(updatedMessage != null){
                context.json(updatedMessage);
                context.status(200);
            } else {
                context.status(400);
            }
        }catch(Exception e){
            context.status(500);
        }
    }

    private void getAllMessagesByUserHandler(Context context) throws JsonProcessingException{
        // ObjectMapper om = new ObjectMapper();
        // Message message = om.readValue(context.body(), Message.class);
        //int message_id = Integer.parseInt(context.pathParam("message_id"));
        int posted_by = Integer.parseInt(context.pathParam("account_id"));
        context.json(messageService.getAllMessagesByUser(posted_by));
        context.status(200);
    }
}