package by.masarnovsky.entity;

public class Account {
    private int id;
    private int owner;
    private double cash;
    private boolean isBlocked;

    public Account(){}

    public Account(int id, int owner, double cash) {
        this.id = id;
        this.owner = owner;
        this.cash = cash;
    }

    public Account(int id, int owner, double cash, boolean isBlocked) {
        this.id = id;
        this.owner = owner;
        this.cash = cash;
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", owner=").append(owner);
        sb.append(", cash=").append(cash);
        sb.append(", isBlocked=").append(isBlocked);
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
}


