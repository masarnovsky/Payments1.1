package by.masarnovsky;

import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginLogic {

    public static Client checkLogin(String enterLogin, String enterPass, HttpServletRequest req){
        Connection connectionn = null;
        ResultSet rs = null;
        Statement statement = null;
        String SQL_SELECT = String.format("select id, fio, password, isAdmin from clients where login='%s'", enterLogin);
        Client client = null;
        try {
            connectionn = DatabaseConnection.getConnection();
            statement = connectionn.createStatement();
            rs = statement.executeQuery(SQL_SELECT);
            int id = 0;
            String fio = null;
            String password = null;
            boolean isAdmin = false;
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
