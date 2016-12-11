package by.masarnovsky;

import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginLogic {

    public static Client checkLogin(String enterLogin, String enterPass, HttpServletRequest req){

        Connection connectionn = null;
        ResultSet rs = null;
        Statement statement = null;
        String SQL_SELECT = String.format("select password, isAdmin from clients where login='%s'", enterLogin);
        Client client = null;
        HttpSession s;
        try {
            connectionn = DatabaseConnection.getConnection();
            statement = connectionn.createStatement();
            rs = statement.executeQuery(SQL_SELECT);
            String password = null;
            boolean isAdmin = false;
            while (rs.next()){
                password = rs.getString(1);
                isAdmin = rs.getBoolean(2);
                if (password.equals(enterPass)){
                    client = new Client(enterLogin, enterPass);
                    req.getSession().setAttribute("login", enterLogin);
                    req.getSession().setAttribute("password", enterLogin);
                    req.getSession().setAttribute("isAdmin", isAdmin);
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
                if (connectionn != null)
                    connectionn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }
}
