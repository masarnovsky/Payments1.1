package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.dao.IAccountDAO;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.implementation.AccountDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class BlockAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.home");
        IAccountDAO accountDAO = new AccountDAO();
        IClientDAO clientDAO = new ClientDAO();
        accountDAO.blockAccount(req);
        clientDAO.setClientAccountToSession((Client) req.getSession().getAttribute("activeClient"), req);
        return page;
    }
}
