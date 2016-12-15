package by.masarnovsky.entity;

public class Client {
    private int id;
    private String fio;
    private String login;
    private String password;
    private boolean isAdmin;

    public Client(){}

    public Client(int id, String fio, String login, String password, boolean isAdmin) {
        this.id = id;
        this.fio = fio;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Client(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Client(int id, String fio, String login) {
        this.id = id;
        this.fio = fio;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
