package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;


    public AccountService() {
        accountDAO = new AccountDAO();
    }


    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    /**
     * 
     * @param account
     * @return Added Account
     */
    public Account addAccount(Account account) {
        if (account.getUsername() == "") {
            return null;
        }
        if (account.getPassword().length() < 4) {
            return null;
        }
        if (accountDAO.getAccountByUsername(account.getUsername()) != null) {
            return null;
        }

        return accountDAO.insertAccount(account);
    }


    /**
     * 
     * @param account
     * @return Retrieved Account
     */
    public Account getAccount(Account account) {
        return accountDAO.getAccount(account);
    }
}
