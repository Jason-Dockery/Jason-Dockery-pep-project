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

    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
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
       /* app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserHandler);*/


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }
            // or Account
    private void postAccountRegistrationHandler(Context context) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account addedUserAccount = accountService.addUser(account);
        if(addedUserAccount != null){
            context.json(addedUserAccount);
            context.status(200);
        }
            context.status(400);
    }
            // or Account
    private void postAccountLoginHandler(Context context) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account userAccount = accountService.accountLogin(null);
        if(userAccount != null){
            context.json(om.writeValueAsString(userAccount));
            context.status(200);
        }
            context.status(401);
    
    }    
 /* 
    private void postMessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        Message messageFeed = messageService.addMessage(null); //consider message.message_text
        if(messageFeed != null){
            context.json(om.writeValueAsString(messageFeed));
            context.status(200);
        }
            context.status(400);
    }

    /*private void getAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
        context.status(200);
        }

    private void getMessageByIdHandler(Context context) {
        ObjectMapper om = new ObjectMapper();
        //Message message = om.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        context.json(messageService.getMessageById(message_id)); //consider just message or message, message_id
        context.status(200);
        }

    private void deleteMessageByIdHandler(Context context) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessageById(message, message_id); //consider just message or message, message_id
        if(deletedMessage != null){
            context.json(om.writeValueAsString(deletedMessage));
            context.status(200);
        }
            context.status(200);
    }

    private void updateMessageByIdHandler(Context context) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message updatedMessage = messageService.updatedMessageById(message, message_id); //consider just message or message, message_id
        if(updatedMessage != null){
            context.json(om.writeValueAsString(updatedMessage));
            context.status(200);
        }
            context.status(400);
    }

    private void getAllMessagesByUserHandler(Context context) {
        int posted_by = Integer.parseInt(context.pathParam("posted_by"));
        context.json(messageService.getAllMessagesByUser(messageService.getMessageById(posted_by), posted_by));
        context.status(200);
    }*/
}