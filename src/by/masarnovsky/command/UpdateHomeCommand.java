package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateHomeCommand implements ActionCommand {
    private final String ACCOUNT_MESSAGE ="accountMessage";

    private final static Logger logger = Logger.getLogger(UpdateHomeCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.home");
        IClientDAO clientDAO = new ClientDAO();
        clientDAO.setClientAccountToSession((Client) req.getSession().getAttribute("activeClient"), req);
        req.getSession().setAttribute(ACCOUNT_MESSAGE, null);
        return page;
    }
}
