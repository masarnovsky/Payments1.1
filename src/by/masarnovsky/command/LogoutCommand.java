package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.index");
        req.getSession().invalidate();
        return page;
    }
}
