package se.iths.webshop.dto;

import jakarta.validation.constraints.NotNull;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>SearchProduct</h2>
 * @date 2024-03-17
 */

public class SearchProduct {

    @NotNull(message="is required")
    private String name;

    @NotNull(message="is required")
    private String category;

    @NotNull(message="is required")
    private String brand;

    public SearchProduct() {
    }

    public SearchProduct(String name, String category, String brand) {
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
