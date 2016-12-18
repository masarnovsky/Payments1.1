package by.masarnovsky.command;

import by.masarnovsky.ConfigurationManager;
import by.masarnovsky.MessageManager;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.dao.IPaymentDAO;
import by.masarnovsky.dao.implementation.ClientDAO;
import by.masarnovsky.dao.implementation.PaymentDAO;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class CreatePaymentCommand implements ActionCommand {
    private static final String ACCOUNT_MESSAGE = "accountMessage";

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.home");
        int id = Integer.valueOf(req.getParameter("idAccountForPayment"));
        double cashForPayment;

        try {
            cashForPayment = Double.valueOf(req.getParameter("cashForPayment"));
            if (cashForPayment < 0){
                throw new NumberFormatException();
            } else {
                IPaymentDAO paymentDAO = new PaymentDAO();
                if (paymentDAO.createPayment(id, cashForPayment)){
                    req.getSession().setAttribute(ACCOUNT_MESSAGE, MessageManager.getProperty("message.createpayment"));
                }
                else {
                    req.getSession().setAttribute(ACCOUNT_MESSAGE, MessageManager.getProperty("message.nocreatepayment"));
                }
                IClientDAO clientDAO = new ClientDAO();
                clientDAO.setClientAccountToSession((Client) req.getSession().getAttribute("activeClient"), req);
            }
        } catch (NumberFormatException e){
            req.getSession().setAttribute(ACCOUNT_MESSAGE, MessageManager.getProperty("message.nocreatepayment"));
            return page;
        }

        return page;
    }
}
