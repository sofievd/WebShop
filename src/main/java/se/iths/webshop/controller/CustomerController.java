package se.iths.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>CustomerController</h2>
 * @date 2024-03-21
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/showCustomerMenu")
    public String showCustomerMenu() {

        return "customer-menu";
    }
}
