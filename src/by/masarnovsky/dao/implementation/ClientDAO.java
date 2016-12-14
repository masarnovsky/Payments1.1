package by.masarnovsky.dao.implementation;

import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.dao.IClientDAO;
import by.masarnovsky.entity.Account;
import by.masarnovsky.entity.Client;
import by.masarnovsky.entity.CreditCard;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDAO implements IClientDAO {
    private static final String CREDIT_CARD_ATTRIBUTE = "creditCardAttribute";
    private static final String ACCOUNTS_ATTRIBUTE = "accountsAttribute";
    private final String INSERT_NEW_CLIENT = "insert into clients(fio, login, password, isAdmin) values(?, ?, ?, 0)";
    private final String CHECK_CLIENT_BY_LOGIN = "select id, fio, password, isAdmin from clients where login=?";
    private final String GET_CLIENT_ACCOUNTS = "select * from account where owner=?";
    private final String GET_ACCOUNT_CARDS = "select * from creditcard where idAccount=?";


    @Override
    public boolean registrateClient(String fio, String login, String password) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(INSERT_NEW_CLIENT);
            ps.setString(1, fio);
            ps.setString(2, login);
            ps.setString(3, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (ps != null)
                    ps.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Client checkLogin(String enterLogin, String enterPass, HttpServletRequest req){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        Client client = null;
        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(CHECK_CLIENT_BY_LOGIN);
            ps.setString(1, enterLogin);
            rs = ps.executeQuery();
            int id;
            String fio;
            String password;
            boolean isAdmin;
            while (rs.next()){
                id = rs.getInt(1);
                fio = rs.getString(2);
                password = rs.getString(3);
                isAdmin = rs.getBoolean(4);
                if (password.equals(enterPass)){
                    client = new Client(id, fio, enterLogin, enterPass, isAdmin);
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeResources(rs, ps, connection);
        }
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public void setClientAccountToSession(Client client, HttpServletRequest req){
        int idClient = client.getId();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<Integer, CreditCard> creditCards = new HashMap<>();
        List<Account> accounts = new ArrayList<>();
        boolean isEmpty = true;


        try{
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareCall(GET_CLIENT_ACCOUNTS);
            ps.setInt(1, idClient);
            rs = ps.executeQuery();

            int idAccount;
            int owner;
            double cash;
            boolean isBlocked;
            while (rs.next()){
                isEmpty = false;
                idAccount = rs.getInt(1);
                owner = rs.getInt(2);
                cash = rs.getDouble(3);
                isBlocked = rs.getBoolean(4);
                accounts.add(new Account(idAccount, owner, cash, isBlocked));
            }
            if (!isEmpty){
                ps = connection.prepareStatement(GET_ACCOUNT_CARDS);
                int account;
                String number;
                int idAccountForCreditCard;
                String valid;
                String cvv;
                for (Account a : accounts){
                    account = a.getId();
                    ps.setInt(1, account);
                    rs = ps.executeQuery();
                    rs.next();
                    number = rs.getString(1);
                    idAccountForCreditCard = rs.getInt(2);
                    valid = rs.getString(3);
                    cvv = rs.getString(4);
                    creditCards.put(account, new CreditCard(number, idAccountForCreditCard, valid, cvv));
                    req.getSession().setAttribute(ACCOUNTS_ATTRIBUTE, accounts);
                    req.getSession().setAttribute(CREDIT_CARD_ATTRIBUTE, creditCards);
                }
            }
            else {
                req.getSession().setAttribute("errorAccounts", true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, connection);
        }
    }

    private void closeResources(ResultSet rs, Statement st, Connection cn){
        try{
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (cn != null)
                cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
