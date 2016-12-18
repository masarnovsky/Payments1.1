package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.MessageManager;
import by.masarnovsky.dao.IAccountDAO;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.implementation.AccountDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.IllegalFormatException;

public class UpdateCashCommand implements ActionCommand{
    private final static Logger logger = Logger.getLogger(UpdateCashCommand.class);

    private static final String ACCOUNT_MESSAGE = "accountMessage";
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.home");
        int id = Integer.valueOf(req.getParameter("idAccount"));
        double cashForUpdate;
        try {
            cashForUpdate = Double.valueOf(req.getParameter("cashForUpdate"));
            if (cashForUpdate < 0){
                throw new NumberFormatException();
            }
            else{
                IAccountDAO accountDAO = new AccountDAO();
                if (accountDAO.updateCash(id, cashForUpdate)){
                    req.getSession().setAttribute(ACCOUNT_MESSAGE, MessageManager.getProperty("message.updatecashsuccessful"));
                }
                IClientDAO clientDAO = new ClientDAO();
                clientDAO.setClientAccountToSession((Client) req.getSession().getAttribute("activeClient"), req);
            }
        }catch (NumberFormatException ex){
            req.getSession().setAttribute(ACCOUNT_MESSAGE, MessageManager.getProperty("message.wrongcashdata"));
            return page;
        }
        return page;
    }
}
