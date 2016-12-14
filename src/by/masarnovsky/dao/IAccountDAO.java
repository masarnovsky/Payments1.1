package by.masarnovsky.dao;

import javax.servlet.http.HttpServletRequest;

public interface IAccountDAO {
    void blockAccount(HttpServletRequest req);
}
