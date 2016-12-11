package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.LoginLogic;
import by.masarnovsky.MessageManager;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String ID = "id";
    private static final String FIO = "fio";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String IS_ADMIN = "isAdmin";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        Client client = LoginLogic.checkLogin(login, password, req);

        if (client != null){
            req.getSession().setAttribute(ID, client.getId());
            req.getSession().setAttribute(FIO, client.getFio());
            req.getSession().setAttribute(LOGIN, client.getLogin());
            req.getSession().setAttribute(PASSWORD, client.getPassword());
            req.getSession().setAttribute(IS_ADMIN, client.isAdmin());
            if ((boolean)req.getSession().getAttribute("isAdmin")){
                page = ConfigurationManager.getProperty("path.page.admin");
            }
            else
                page = ConfigurationManager.getProperty("path.page.user");
        } else {
            req.setAttribute("errorLoginOrPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
