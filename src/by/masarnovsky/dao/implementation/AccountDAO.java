package by.masarnovsky.dao.implementation;

import by.masarnovsky.MessageManager;
import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.dao.IAccountDAO;
import by.masarnovsky.entity.Account;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountDAO implements IAccountDAO {
    private final static Logger logger = Logger.getLogger(AccountDAO.class);

    private final String BLOCKING_MESSAGE = "accountMessage";
    private final String UNBLOCKING_MESSAGE = "unblockingMessage";

    private static final String BLOCK_ACCOUNT = "UPDATE account SET isBlocked=\'1\' WHERE id=?";
    private final static String GET_BLOCKED_ACCOUNTS = "select * from account where isBlocked=1";
    private final static String UNBLOCK_ACCOUNT = "update account set isBlocked=\'0\' where id=?";
    private final static String CREATE_ACCOUNT = "insert into account values (?, ?, 0.0, 0)";

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

    @Override
    public void unblockAccount(HttpServletRequest req) {
        String accountToUnblock = (String) req.getSession().getAttribute("setAccountToUnblock");
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(UNBLOCK_ACCOUNT);
            ps.setInt(1, Integer.valueOf(accountToUnblock));
            int updates = ps.executeUpdate();
            if (updates == 0)
                req.getSession().setAttribute(UNBLOCKING_MESSAGE, MessageManager.getProperty("message.wrongunblocking"));
            else
                req.getSession().setAttribute(UNBLOCKING_MESSAGE, MessageManager.getProperty("message.successunblocking"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, connection);
        }
    }

    @Override
    public List<Account> getBlockedAccounts() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> accounts = new ArrayList<>();

        try{
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_BLOCKED_ACCOUNTS);
            rs = ps.executeQuery();
            while (rs.next()){
                accounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, connection);
        }
        return accounts;
    }

    @Override
    public Account createAccount(int owner) {
        Account account = null;
        Random rand = new Random();
        int id = rand.nextInt(5000)+2000;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(CREATE_ACCOUNT);
            ps.setInt(1, id);
            ps.setInt(2, owner);
            ps.executeUpdate();
            account = new Account(id, owner, 0.0, false);
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, connection);
        }
        return account;
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
