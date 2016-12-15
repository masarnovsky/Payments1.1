package by.masarnovsky.dao;

import by.masarnovsky.entity.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAccountDAO {
    void blockAccount(HttpServletRequest req);
    void unblockAccount(HttpServletRequest req);
    List<Account> getBlockedAccounts();
}
