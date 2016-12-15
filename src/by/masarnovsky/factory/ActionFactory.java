package by.masarnovsky.factory;

import by.masarnovsky.MessageManager;
import by.masarnovsky.command.ActionCommand;
import by.masarnovsky.command.CommandEnum;
import by.masarnovsky.command.EmptyCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private final static Logger logger = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest req){
        ActionCommand current = new EmptyCommand();
        String action = req.getParameter("command");
        if (action == null || action.isEmpty()){
            return current;
        }

        try{
            String act = action;
            String setAccountToDelete = null;
            String setAccountToUnblock = null;
            if (action.contains("unblockAccount")) {
                setAccountToUnblock = action.substring(14);
                act = "unblockAccount";
                req.getSession().setAttribute("setAccountToUnblock", setAccountToUnblock);
            }
             else if (action.contains("blockAccount")){
                setAccountToDelete = action.substring(12);
                act = "blockAccount";
                req.getSession().setAttribute("setAccountToDelete", setAccountToDelete);
            }
            CommandEnum currentEnum = CommandEnum.valueOf(act.toUpperCase());
            current = currentEnum.getCurrentCommand();
        }catch (IllegalArgumentException ex) {
            req.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
