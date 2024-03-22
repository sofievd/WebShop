package se.iths.webshop.repository.model;

import jakarta.persistence.*;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.Product;

@Entity
@Table(name = "orderline")
public class Orderline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "product_quantity")
    private int amount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
