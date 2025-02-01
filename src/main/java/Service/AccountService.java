package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

     public Account accountLogin (String username){
        // edit more to fit needs of readme.md ##2
        if(accountDAO.accountLogin(username)){
            // Account userAccount = accountDAO.accountLogin(username);
            // return userAccount;
            // fix this
        }
             return null;
    }
// or ;account // !accountDAO.accountLogin(account.getUsername()) edit more to fit needs of readme.md ##1
    public Account addUser (Account account){
        // String username = account.getUsername();
        // String password = account.getPassword();
        
        if(account.getUsername().length() > 0 && 
           account.getPassword().length() >= 4 && 
           account.username != account.getUsername() //fix this
        ){
            Account addedUserAccount = accountDAO.addUser(account);
            return addedUserAccount;
        } else
            return null;
    }
}
