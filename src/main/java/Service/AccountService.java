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

     public Account accountLogin (Account account){
        // edit more to fit needs of readme.md ##2
        if(accountDAO.getAccount(account) != null){
            Account userAccount = accountDAO.getAccount(account);
            return userAccount;
        }
            return null;
    }
// or ;account // !accountDAO.accountLogin(account.getUsername()) edit more to fit needs of readme.md ##1
    public Account addUser (Account account){
        // String username = account.getUsername();
        // String password = account.getPassword();
        
        if(account.getUsername().length() > 0 && 
           account.getPassword().length() >= 4 && 
           actualAccount(account.getUsername()) //fix this
        ){
            Account addedUserAccount = accountDAO.addUserAccount(account);
            return addedUserAccount;
        } else
            return null;
    }

    private boolean actualAccount (String username){
        if(accountDAO.getAccountLogin(username) == null){
            return true;
        }
            return false;
    }
}
