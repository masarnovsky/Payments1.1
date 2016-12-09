package by.masarnovsky;

public class LoginLogic {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASSWORD = "1";

    public static boolean checkLogin(String enterLogin, String enterPass){
        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASSWORD.equals(enterPass);
    }
}
