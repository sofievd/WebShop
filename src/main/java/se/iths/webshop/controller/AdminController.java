package se.iths.webshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.webshop.controller.model.AdminMenu;
import se.iths.webshop.repository.model.User;

import java.util.List;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>AdminController</h2>
 * @date 2024-03-16
 */
@Controller
public class AdminController {

    @Value("${admin-tasks}")
    private List<String> adminTasks;

    @GetMapping("/showAdminMenu")
    public String showAdminMenu(Model model) {
        System.out.println(adminTasks);
        AdminMenu menu = new AdminMenu();
        model.addAttribute("menu", menu);
        model.addAttribute("adminTasks", adminTasks);
        return "admin-menu";
    }

    @PostMapping("/processAdminChoice")
    public String processAdminChoice(@ModelAttribute("menu") AdminMenu menu) {
        System.out.println(menu.getInputChoice());
        switch (menu.getInputChoice()) {
            case "Add a product" -> {return "add-product";}
            case "Update a product" -> {return "update-product";}
            default -> {return "admin-menu";}
        }
    }
}
