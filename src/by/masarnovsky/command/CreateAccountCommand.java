package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.dao.IAccountDAO;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.ICreditCardDAO;
import by.masarnovsky.dao.implementation.AccountDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.dao.implementation.CreditCardDAO;
import by.masarnovsky.entity.Account;
import by.masarnovsky.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateAccountCommand implements ActionCommand{

    private final static Logger logger = Logger.getLogger(CreateAccountCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.home");
        int idOwner = (int) req.getSession().getAttribute("id");
        IAccountDAO accountDAO = new AccountDAO();
        ICreditCardDAO creditCardDAO = new CreditCardDAO();
        IClientDAO clientDAO = new ClientDAO();
        Account account = accountDAO.createAccount(idOwner);
        if (account != null)
            creditCardDAO.createCreditCard(account.getId());
        else
            logger.info("error in create CC");
        clientDAO.setClientAccountToSession((Client) req.getSession().getAttribute("activeClient"), req);
        return page;
    }
}
