package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.MessageManager;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String ID = "id";
    private static final String FIO = "fio";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String IS_ADMIN = "isAdmin";
    private static final String IS_SIGNED = "isSignedIn";
    private static final String ACTIVE_CLIENT = "activeClient";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        IClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.checkLogin(login, password, req);

        if (client != null){
            req.getSession().setAttribute(ID, client.getId());
            req.getSession().setAttribute(FIO, client.getFio());
            req.getSession().setAttribute(LOGIN, client.getLogin());
            req.getSession().setAttribute(PASSWORD, client.getPassword());
            req.getSession().setAttribute(IS_ADMIN, client.isAdmin());
            req.getSession().setAttribute(IS_SIGNED, true);
            req.getSession().setAttribute(ACTIVE_CLIENT, client);
            clientDAO.setClientAccountToSession(client, req);
            page = ConfigurationManager.getProperty("path.page.home");
        } else {
            req.setAttribute("errorLoginOrPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
