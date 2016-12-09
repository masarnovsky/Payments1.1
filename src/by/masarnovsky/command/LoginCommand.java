package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.LoginLogic;
import by.masarnovsky.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        String login = req.getParameter(PARAM_NAME_LOGIN);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        if (LoginLogic.checkLogin(login, password)){
            req.setAttribute("user", login);
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            req.setAttribute("errorLoginOrPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
            //req.setAttribute("errorLoginOrPassMessage", "wrong");
            //page = "/WEB-INF/jsp/login.jsp";
        }
        return page;
    }
}
