package se.iths.webshop.controller.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>WebProduct</h2>
 * @date 2024-03-16
 */
public class WebProduct {

    private int id;

    @NotNull(message="is required")
    private String name;

    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to 0")
    @Max(value=2000, message="must be less than or equal to 2000")
    private double price;

    @NotNull(message="is required")
    private String category;

    @NotNull(message="is required")
    private String description;

    @NotNull(message="is required")
    private String brand;

    public WebProduct() {
    }

    public WebProduct(String name, double price, String category, String description, String brand) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.brand = brand;
    }

    public WebProduct(int id, String name, double price, String category, String description, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "WebProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
