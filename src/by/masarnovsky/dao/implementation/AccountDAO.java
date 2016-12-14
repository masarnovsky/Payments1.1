package by.masarnovsky.dao.implementation;

import by.masarnovsky.MessageManager;
import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.dao.IAccountDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class AccountDAO implements IAccountDAO {
    private final String BLOCKING_MESSAGE = "blockingMessage";

    private static final String BLOCK_ACCOUNT = "UPDATE account SET isBlocked=\'1\' WHERE id=?";

    @Override
    public void blockAccount(HttpServletRequest req) {
        String accountToDelete = (String) req.getSession().getAttribute("setAccountToDelete");
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(BLOCK_ACCOUNT);
            ps.setInt(1, Integer.valueOf(accountToDelete));
            int updates = ps.executeUpdate();
            if (updates == 0)
                req.getSession().setAttribute(BLOCKING_MESSAGE, MessageManager.getProperty("message.wrongblocking"));
            else
                req.getSession().setAttribute(BLOCKING_MESSAGE, MessageManager.getProperty("message.successblocking"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, connection);
        }
    }

    private void closeResources(ResultSet rs, Statement st, Connection cn){
        try{
            if (rs != null)
                rs.close();
            closeResources(st, cn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResources(Statement st, Connection cn){
        try{
            if (st != null)
                st.close();
            if (cn != null)
                cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
