package se.iths.webshop.entity;

import jakarta.persistence.*;
import se.iths.webshop.entity.User;

import java.sql.Date;

@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="customer_email")
    private User user;

    private Date date;

    @Column(name="total_amount")
    private double totalAmount;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
