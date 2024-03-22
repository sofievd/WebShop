package se.iths.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
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
import se.iths.webshop.dto.CategoryMenu;
import se.iths.webshop.entity.Category;
import se.iths.webshop.service.CategoryService;
import se.iths.webshop.entity.Product;
import se.iths.webshop.service.CategoryService;
import se.iths.webshop.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
     @Autowired
    private ProductService pService;
     @Autowired
    private CategoryService cService;

    @Value("${categories}")
    private List<String> categories;


     @GetMapping("/webShop")
     public String CategoryList(Model m){
         CategoryMenu menu= new CategoryMenu();
         m.addAttribute("menu", menu);
          m.addAttribute("categoryList", cService.getCataegories());
          return "webshoppage";
     }
  /*   @RequestMapping("/processLoginForm")
     public String forwardUrl(){
         return "forward:/webshop";
     }*/

     @PostMapping("/search")
     public String search(Model m, @RequestParam("search") String searchWord){
          List<Product> productsWithSameNameList = pService.searchProducts(searchWord);
          if(!productsWithSameNameList.isEmpty()){
              m.addAttribute("productList", productsWithSameNameList);
              m.addAttribute("categoryList", cService.getCataegories());
              return "choose-product-from-list";
          } else {
              m.addAttribute("Product ", searchWord + " cannot be found!");
              return "no-product-found";
          }
     }

     @PostMapping("/chooseQuantityOfProduct")
     public String chooseQuantityOfProduct(@RequestParam("id") int id, Model model) {

         Product desiredProduct = pService.findProductById(id);
         model.addAttribute("product", desiredProduct);

         return "choose-quantity-of-product";
     }

    @PostMapping("/addProductToBasket")
    public String addProductToBasket(@Valid @ModelAttribute("id") int id,
                                     @Valid @ModelAttribute("quantity") int quantity,
                                     BindingResult theBindingResult, Model model) {

        Product desiredProduct = pService.findProductById(id);

        if (theBindingResult.hasErrors()) {
            model.addAttribute("productDto", desiredProduct);
            return "choose-quantity-of-product";
        } else {

            //TODO add desiredProduct with quantity to basket
            return "redirect:/product/webShop?success";
        }
    }

     @PostMapping("/chooseCategory")
     public String chooseCategory(Model model, @ModelAttribute("menu") CategoryMenu menu, @RequestParam("categoryID") int id){
         Category category = cService.findById(id);
         model.addAttribute("category", category);
         List<Product> productList = pService.getProductByCategory(category.getName());
         model.addAttribute("productlist", productList);
          return "category-page";
     }

     @GetMapping("/category")
     public String category(Model m){
         String chosencategory = null;
         List<Product> productList = pService.getProductByCategory(chosencategory);
         return "category-page";
     }

     @GetMapping("/all-products")
     public String ProductList(Model m){
         m.addAttribute("allproductslist", pService.getProducts());
         return "show-products";
     }


    // add an InitBinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /* @GetMapping("/categorypage")
     public String catecories(Model m){
          return "categorypage";
     }
*/

}
