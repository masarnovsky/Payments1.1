package by.masarnovsky.dao;

public interface IPaymentDAO {
    boolean createPayment(int idAccount, double cash);
}
