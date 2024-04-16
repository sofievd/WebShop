package se.iths.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;
import se.iths.webshop.service.CategoryService;
import se.iths.webshop.service.ProductService;

import java.util.List;

/**
 * @author Sofie Van Dingenen, Depinder Kaur
 * @version 1.0
 * <h2> ProductController </h2></>
 * @date 2024-04-08
 */


@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService pService;
    @Autowired
    private CategoryService cService;


    @GetMapping("/webShop")
    public String CategoryList(Model m) {
        m.addAttribute("categoryList", cService.getCataegories());
        return "customer/web-shop-page";
    }

    @PostMapping("/search")
    public String search(Model m, @RequestParam("search") String searchWord) {
        List<Product> productsWithSameNameList = pService.searchProducts(searchWord);
        if (!productsWithSameNameList.isEmpty()) {
            m.addAttribute("productList", productsWithSameNameList);
            m.addAttribute("categoryList", cService.getCataegories());
            return "customer/choose-product-from-list";
        } else {
            m.addAttribute("Product ", searchWord + " cannot be found!");
            return "customer/product-not-found";
        }
    }

    @PostMapping("/chooseCategory")
    public String chooseCategory(@RequestParam("categoryID") int id,
                                 Model model) {
        Category category = cService.findById(id);
        model.addAttribute("category", category);
        List<Product> productList = pService.getProductByCategory(category.getName());
        model.addAttribute("productlist", productList);
        return "customer/category-page";
    }

    @GetMapping("/all-products")
    public String ProductList(Model m) {
        m.addAttribute("allproductslist", pService.getProducts());
        return "customer/show-products";
    }

}
