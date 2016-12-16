package by.masarnovsky.dao.implementation;

import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.dao.ICreditCardDAO;
import by.masarnovsky.entity.CreditCard;

import java.sql.*;
import java.util.Random;

public class CreditCardDAO implements ICreditCardDAO {
    private static final String CREATE_CREDIT_CARD = "insert into creditcard values(?, ?, ?, ?)";

    @Override
    public boolean createCreditCard(int idAccount) {
        CreditCard card = null;
        Random rand = new Random();
        String number = String.valueOf(rand.nextInt(6000)+3000) + " " +
                String.valueOf(rand.nextInt(6000)+3000) + " " +
                String.valueOf(rand.nextInt(6000)+3000) + " " +
                String.valueOf(rand.nextInt(6000)+3000);
        String valid = "12/20";
        String cvv = "160";
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(CREATE_CREDIT_CARD);
            ps.setString(1, number);
            ps.setInt(2, idAccount);
            ps.setString(3, valid);
            ps.setString(4, cvv);
            int updates = ps.executeUpdate();
            if (updates > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(ps, connection);
        }

        return false;
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
