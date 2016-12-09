package by.masarnovsky.factory;

import by.masarnovsky.MessageManager;
import by.masarnovsky.command.ActionCommand;
import by.masarnovsky.command.CommandEnum;
import by.masarnovsky.command.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest req){
        ActionCommand current = new EmptyCommand();
        String action = req.getParameter("command");
        if (action == null || action.isEmpty()){
            return current;
        }

        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        }catch (IllegalArgumentException ex) {
            req.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
