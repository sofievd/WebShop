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
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshop.controller.model.AdminMenu;
import se.iths.webshop.controller.model.SearchProduct;
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
        //System.out.println(menu.getInputChoice());
        switch (menu.getInputChoice()) {
            case "Add a product" -> {
                return "redirect:/addProduct";
            }
            case "Update a product" -> {
                return "redirect:/searchProduct";
            }
            case "Show Orders" -> {
                return "admin-tasks/all-orders";
            }
            default -> {
                return "user/admin-menu";
            }
        }
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        WebProduct webProduct = new WebProduct();
        model.addAttribute("webProduct", webProduct);
        model.addAttribute("categories", categories);
        return "admin-tasks/add-product";
    }

    @PostMapping("/processAddProduct")
    public String processAddProduct(@Valid @ModelAttribute("webProduct") WebProduct webProduct,
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
            return "admin-tasks/added-confirmation";
        }
    }

    @GetMapping("/searchProduct")
    public String searchProduct(Model model) {
        SearchProduct searchProduct = new SearchProduct();
        model.addAttribute("searchProduct", searchProduct);
        model.addAttribute("categories", categories);
        return "admin-tasks/search-product";
    }

    @PostMapping("/searchProduct")
    public String processSearchProduct(@Valid @ModelAttribute("searchProduct") SearchProduct searchProduct,
                                      BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            //System.out.println(theBindingResult);
            model.addAttribute("categories", categories);
            return "admin-tasks/search-product";
        } else {
            Category category = cService.getCategoryByName(searchProduct.getCategory());
            List<Product> productList = pService.findByNameAndCategoryAndBrand(searchProduct.getName(), category, searchProduct.getBrand());

            if (productList.isEmpty()) {
                return "admin-tasks/product-not-found";
            }
            model.addAttribute("productList", productList);
            return "admin-tasks/choose-product-to-update";
        }
    }

    @PostMapping("/selectProductToUpdate")
    public String selectProductToUpdate(@RequestParam("id") int id, Model model) {

        Product desiredProduct = pService.findProductById(id);
        String category = desiredProduct.getCategory().getName();
        WebProduct webProduct = new WebProduct(desiredProduct.getId(), desiredProduct.getName(), desiredProduct.getPrice(),
                                                category, desiredProduct.getDescription(), desiredProduct.getBrand());

        model.addAttribute("webProduct", webProduct);
        model.addAttribute("categories", categories);
        model.addAttribute("id", id);
        return "admin-tasks/update-product";
    }

    @PostMapping("/updateProduct")
    public String processUpdateProduct(@RequestParam("id") int id, @ModelAttribute("webProduct") WebProduct webProduct,
                                       BindingResult theBindingResult) {

        webProduct.setId(id);

        if (theBindingResult.hasErrors()) {
            return "redirect:/desiredProduct";
        } else {
            Product product = pService.findProductById(id);
            product.setName(webProduct.getName());
            product.setPrice(webProduct.getPrice());

            Category category = cService.getCategoryByName(webProduct.getCategory());
            product.setCategory(category);

            product.setDescription(webProduct.getDescription());
            product.setBrand(webProduct.getBrand());

            pService.saveProduct(product);
            return "admin-tasks/update-confirmation";
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