package by.masarnovsky.dao.implementation;

import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.dao.IClientDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO implements IClientDAO {
    private final String INSERT_NEW_CLIENT = "insert into clients(fio, login, password, isAdmin) values(?, ?, ?, 0)";
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
}
