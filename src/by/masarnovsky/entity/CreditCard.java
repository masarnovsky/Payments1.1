package by.masarnovsky.entity;

public class CreditCard {
    private String number;
    private int account;
    private String valid;
    private String cvv;

    public CreditCard(String number, int account, String valid, String cvv) {
        this.number = number;
        this.account = account;
        this.valid = valid;
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
