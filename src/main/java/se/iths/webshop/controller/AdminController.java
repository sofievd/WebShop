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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshop.dto.AdminMenu;
import se.iths.webshop.dto.ProductDto;
import se.iths.webshop.dto.UserDto;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.Product;
import se.iths.webshop.service.CategoryService;
import se.iths.webshop.service.OrderService;
import se.iths.webshop.service.ProductService;
import se.iths.webshop.service.UserService;

import java.util.List;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>AdminController</h2>
 * @date 2024-03-16
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService pService;

    @Autowired
    private CategoryService cService;

    @Autowired
    private OrderService orderService;

    @Value("${admin-tasks}")
    private List<String> adminTasks;

    @Value("${categories}")
    private List<String> categories;

    @GetMapping("/showAdminMenu")
    public String showAdminMenu(Model model) {
        AdminMenu menu = new AdminMenu();
        model.addAttribute("menu", menu);
        model.addAttribute("adminTasks", adminTasks);
        return "admin/admin-menu";
    }

    @PostMapping("/processAdminChoice")
    public String processAdminChoice(@ModelAttribute("menu") AdminMenu menu) {

        switch (menu.getInputChoice()) {
            case "Add a product" -> {
                return "redirect:/admin/addProduct";
            }
            case "Update a product" -> {
                return "redirect:/product/webShop";
            }
            case "See All Orders" -> {
                return "redirect:/admin/allOrders";
            }
            case "See All Users" -> {
                return "redirect:/admin/usersList";
            }
            default -> {
                return "admin/admin-menu";
            }
        }
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        model.addAttribute("categories", cService.getCataegories());
        return "admin/add-product";
    }

    @PostMapping("/addProduct/save")
    public String processAddProduct(@Valid @ModelAttribute("productDto") ProductDto productDto,
                                    BindingResult theBindingResult, Model model) {

        if (theBindingResult.hasErrors()) {
            model.addAttribute("categories", cService.getCataegories());
            return "admin/add-product";
        } else {
            Product product = getProductFromProductDto(productDto, new Product());
            pService.saveProduct(product);
            return "redirect:/admin/addProduct?success";
        }
    }

    @PostMapping("/selectProductToUpdate")
    public String selectProductToUpdate(@RequestParam("id") int id, Model model) {

        Product desiredProduct = pService.findProductById(id);
        String category = desiredProduct.getCategory().getName();
        ProductDto productDto = new ProductDto(desiredProduct.getId(), desiredProduct.getName(),
                                                desiredProduct.getPrice(), category,
                                                desiredProduct.getDescription(), desiredProduct.getBrand());

        model.addAttribute("productDto", productDto);
        model.addAttribute("categories", categories);
        model.addAttribute("id", id);
        return "admin/update-product";
    }

    @PostMapping("/updateProduct")
    public String processUpdateProduct(@RequestParam("id") int id,
                                       @Valid @ModelAttribute("productDto") ProductDto productDto,
                                       BindingResult theBindingResult,
                                       Model model) {
        productDto.setId(id);

        if (theBindingResult.hasErrors()) {
            model.addAttribute("productDto", productDto);
            model.addAttribute("categories", categories);
            model.addAttribute("id", id);
            return "admin/update-product";
        } else {
            Product product = pService.findProductById(id);
            Product updatedProduct = getProductFromProductDto(productDto, product);
            pService.saveProduct(updatedProduct);
            return "redirect:/product/webShop?success";
        }
    }

    private Product getProductFromProductDto(ProductDto productDto, Product product) {
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Category category = cService.getCategoryByName(productDto.getCategory());
        product.setCategory(category);

        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        return product;
    }

    @GetMapping("/usersList")
    public String usersList(Model model) {
        List<UserDto> usersList = userService.findAllUsers();
        model.addAttribute("usersList", usersList);
        return "admin/users-list";
    }

    @GetMapping("/allOrders")
    public String allOrders(Model model) {
        List<Order> allOrdersList = orderService.getAllOrders();
        model.addAttribute("ordersList", allOrdersList);
        return "admin/all-orders";
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