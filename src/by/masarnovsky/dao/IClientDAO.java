package by.masarnovsky.dao;

import by.masarnovsky.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IClientDAO {
    boolean registrateClient(String fio, String login, String password);
    List<Client> getAllClients();
    Client checkLogin(String enterLogin, String enterPass, HttpServletRequest req);
    void setClientAccountToSession(Client client, HttpServletRequest req);
}
