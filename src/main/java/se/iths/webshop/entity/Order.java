package se.iths.webshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
/**
 * @author Sofie Van Dingenen
 * @version 1.0
 * <h2> Order </h2></>
 * @date 2024-04-08
 */

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="customer_email")
    private User user;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="total_amount")
    private double totalAmount;

    @Column(name = "status")
    private String status;

    public Order() {
    }

    public Order(User user, LocalDateTime date, double totalAmount, String status) {
        this.user = user;
        this.date = date;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(int id, User user, LocalDateTime date, double totalAmount, String status) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(int id){
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user.getFirstName() + " " + user.getLastName() +
                ", email=" + user.getEmail() +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
