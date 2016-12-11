package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.LoginLogic;
import by.masarnovsky.MessageManager;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        String login = req.getParameter(PARAM_NAME_LOGIN);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        Client client = LoginLogic.checkLogin(login, password, req);

        if (client != null){
            req.setAttribute("login", login);
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
