package by.masarnovsky.entity;

import java.sql.Date;
import java.sql.Time;

public class Payment {
    private int id;
    private int idCreditCard;
    private double summ;
    private Date date;
    private Time time;

    Payment(){}

    public Payment(int id, int idCreditCard, double summ, Date date, Time time) {
        this.id = id;
        this.idCreditCard = idCreditCard;
        this.summ = summ;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCreditCard() {
        return idCreditCard;
    }

    public void setIdCreditCard(int idCreditCard) {
        this.idCreditCard = idCreditCard;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
