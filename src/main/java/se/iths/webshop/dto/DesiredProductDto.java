package se.iths.webshop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import se.iths.webshop.entity.Category;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>DesiredProductDto</h2>
 * @date 2024-03-22
 */
public class DesiredProductDto {

    private int id;

    @NotNull(message="is required")
    private String name;

    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to 0")
    @Max(value=2000, message="must be less than or equal to 2000")
    private double price;

    @NotNull(message="is required")
    private Category category;

    @NotNull(message="is required")
    private String description;

    @NotNull(message="is required")
    private String brand;

    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to 0")
    @Max(value=50, message="must be less than or equal to 50")
    private int quantity;

    public DesiredProductDto() {
    }

    public DesiredProductDto(int id, String name, double price, Category category, String description, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.brand = brand;
        this.quantity = 0;
    }

    public DesiredProductDto(int id, String name, double price, Category category, String description, String brand, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.brand = brand;
        this.quantity = quantity;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DesiredProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
