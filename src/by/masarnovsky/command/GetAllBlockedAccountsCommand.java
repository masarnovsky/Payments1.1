package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.dao.IAccountDAO;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.implementation.AccountDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.entity.Account;
import by.masarnovsky.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllBlockedAccountsCommand implements ActionCommand {
    private final String UNBLOCKING_MESSAGE = "unblockingMessage";

    private static final String BLOCKED_ACCOUNTS = "blockedAccounts";
    private static final String CLIENTS_OF_BLOCKING_ACCOUNTS = "clientsOfBlockingAccounts";
    private static final String ZERO_BLOCKED_ACCOUNTS = "zeroBlockedAccounts";

    private final static Logger logger = Logger.getLogger(GetAllBlockedAccountsCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().setAttribute(UNBLOCKING_MESSAGE, null);
        String page = ConfigurationManager.getProperty("path.page.blockedaccounts");
        IClientDAO clientDAO = new ClientDAO();
        IAccountDAO accountDAO = new AccountDAO();
        List<Account> blockedAccounts = accountDAO.getBlockedAccounts();
        req.getSession().setAttribute(BLOCKED_ACCOUNTS, blockedAccounts);
        if (blockedAccounts.size() > 0){
            Map<Integer, Client> clients = new HashMap<>();
            for (Account a: blockedAccounts){
                clients.put(a.getId(), clientDAO.getClientById(a.getOwner()));
            }
            req.getSession().setAttribute(CLIENTS_OF_BLOCKING_ACCOUNTS, clients);
        } else{
            req.getSession().setAttribute(ZERO_BLOCKED_ACCOUNTS, true);
        }
        return page;
    }
}
