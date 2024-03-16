package se.iths.webshop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.webshop.controller.model.AdminMenu;
import se.iths.webshop.controller.model.WebProduct;
import se.iths.webshop.repository.model.Category;
import se.iths.webshop.repository.model.Product;
import se.iths.webshop.service.CategoryService;
import se.iths.webshop.service.ProductService;

import java.util.List;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>AdminController</h2>
 * @date 2024-03-16
 */
@Controller
public class AdminController {

    @Autowired
    private ProductService pService;

    @Autowired
    private CategoryService cService;

    @Value("${admin-tasks}")
    private List<String> adminTasks;

    @Value("${categories}")
    private List<String> categories;


    @GetMapping("/showAdminMenu")
    public String showAdminMenu(Model model) {
        AdminMenu menu = new AdminMenu();
        model.addAttribute("menu", menu);
        model.addAttribute("adminTasks", adminTasks);
        return "user/admin-menu";
    }

    @PostMapping("/processAdminChoice")
    public String processAdminChoice(@ModelAttribute("menu") AdminMenu menu) {
        System.out.println(menu.getInputChoice());
        switch (menu.getInputChoice()) {
            case "Add a product" -> {
                return "redirect:/addNewProduct";
            }
            case "Show Products" -> {
                return "admin-tasks/all-products";
            }
            case "Show Orders" -> {
                return "admin-tasks/all-orders";
            }
            default -> {
                return "user/admin-menu";
            }
        }
    }

    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model) {
        WebProduct webProduct = new WebProduct();
        model.addAttribute("webProduct", webProduct);
        model.addAttribute("categories", categories);
        return "admin-tasks/add-product";
    }

    @PostMapping("/processNewProduct")
    public String productAddedConfirmation(@Valid @ModelAttribute("webProduct") WebProduct webProduct,
                                           BindingResult theBindingResult, Model model) {

        if (theBindingResult.hasErrors()) {
            model.addAttribute("categories", categories);
            return "admin-tasks/add-product";
        } else {
            Product product = new Product();
            product.setName(webProduct.getName());
            product.setPrice(webProduct.getPrice());

            Category category = cService.getCategoryByName(webProduct.getCategory());
            product.setCategory(category);

            product.setDescription(webProduct.getDescription());
            product.setBrand(webProduct.getBrand());

            pService.saveProduct(product);
            return "admin-tasks/product-added-confirmation";
        }
    }

    // add an InitBinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}