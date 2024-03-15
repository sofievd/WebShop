package se.iths.webshop.controller.model;

import jakarta.validation.constraints.NotNull;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>WebUser</h2>
 * @date 2024-03-15
 */
public class WebUser {

    @NotNull(message = "is required")
    private String email;

    @NotNull(message = "is required")
    private String password;

    public WebUser() {
    }

    public WebUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
