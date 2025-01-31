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
    //     // edit more to fit needs of readme.md ##2
    //     String username = a
    //             // or ;account
    //     if(!account.username.isBlank() && !account.password.isBlank()){
    //         return accountDAO.accountLogin(username);
    //     }
             return accountDAO.addUser(null);
    }
// or ;account account.password.length() > 3 && // edit more to fit needs of readme.md ##1
    public Account addUser (Account account){
        // String username = account.getUsername();
        // String password = account.getPassword();
        
        if(account.getUsername().length() > 0 && account.getPassword().length() >= 4 && !accountDAO.accountLogin(account.getUsername())){
            return null;
        }
        // if(){
        //     return null;
        // }
        // if(!accountDAO.accountLogin(username)){
        //     Account addedUserAccount = accountDAO.addUser(account);
        //     return addedUserAccount;
        // }
            return null;
    }
}
