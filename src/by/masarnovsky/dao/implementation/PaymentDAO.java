package by.masarnovsky.dao.implementation;

import by.masarnovsky.dao.DatabaseConnection;
import by.masarnovsky.dao.IPaymentDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class PaymentDAO implements IPaymentDAO {
    private final static Logger logger = Logger.getLogger(PaymentDAO.class);

    private final String CREATE_PAYMENT = "insert into payment(idAccount, summ, date, time) values( ?, ?, ?, ?)";
    private final String GET_ACCOUNT_CASH = "select cash from account where id=?";
    private final String UPDATE_ACCOUNT_CASH = "update account set cash=? where id=?";

    @Override
    public boolean createPayment(int idAccount, double cash) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isSuccess = false;
        double currentCash;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_ACCOUNT_CASH);
            ps.setInt(1, idAccount);
            rs = ps.executeQuery();
            rs.next();
            currentCash = rs.getDouble(1);
            if (currentCash >= cash) {
                ps = connection.prepareStatement(UPDATE_ACCOUNT_CASH);
                double i = currentCash-cash;
                ps.setDouble(1, i);
                ps.setInt(2, idAccount);
                ps.executeUpdate();
                ps = connection.prepareStatement(CREATE_PAYMENT);
                ps.setInt(1, idAccount);
                ps.setDouble(2, cash);
                Calendar calendar = Calendar.getInstance();
                long now = calendar.getTimeInMillis();
                java.sql.Time sqlTime = new java.sql.Time(now);
                java.sql.Date sqlDate = new java.sql.Date(now);
                ps.setDate(3, sqlDate);
                ps.setTime(4, sqlTime);
                int updates = ps.executeUpdate();
                if (updates > 0){
                    isSuccess = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }
}
