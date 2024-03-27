package se.iths.webshop.dto;

import se.iths.webshop.entity.User;

import java.time.LocalDateTime;

/**
 * @author Depinder Kaur
 * @version <h2></h2>
 * @date 2024-03-26
 */
public class OrderDto {
    private User user;
    private LocalDateTime date;
    private double totalAmount;

    public OrderDto() {
    }

    public OrderDto(User user, LocalDateTime date, double totalAmount) {
        this.user = user;
        this.date = date;
        this.totalAmount = totalAmount;
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

    @Override
    public String toString() {
        return "OrderDto{" +
                "user=" + user +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
