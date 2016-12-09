package by.masarnovsky.entity;

public class Client {
    private int id;
    private String fio;
    private String login;
    private String password;

    public Client(){}

    public Client(int id, String fio, String login, String password) {
        this.id = id;
        this.fio = fio;
        this.login = login;
        this.password = password;
    }

    public Client(String fio, String login, String password) {
        this.fio = fio;
        this.login = login;
        this.password = password;
    }
}
