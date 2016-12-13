package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.MessageManager;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class SigninCommand implements ActionCommand {
    private static final String ID = "id";
    private static final String FIO = "fio";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "passwordRepeat";
    private static final String ERROR_IN_PASSWORD = "errorInPass";
    private static final String ERROR_IN_LOGIN = "errorInLogin";
    private static final String IS_ADMIN = "isAdmin";
    private static final String ERROR_IN_SIGNIN = "errorSIGNIN";

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.signin");;
        String fio = req.getParameter(FIO);
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String repeatPassword = req.getParameter(REPEAT_PASSWORD);

        if (password != null && password.equals(repeatPassword)){
            IClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.checkLogin(login, password, req);
            if (client == null){
                if (clientDAO.registrateClient(fio, login, password)){
                    page = ConfigurationManager.getProperty("path.page.success");
                }
                else {
                    req.setAttribute(ERROR_IN_SIGNIN, "Error in signin");
                }
            }
            else {
                req.setAttribute(ERROR_IN_LOGIN, MessageManager.getProperty("message.onlyloginerror"));
            }
        }
        else {
            req.setAttribute(ERROR_IN_PASSWORD, MessageManager.getProperty("message.onlypassworderror"));
        }
        return page;
    }
}
