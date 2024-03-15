package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
     @Autowired
     ProductService service;

     @GetMapping("/webshop")
     public String CategoryList(Model m){
          m.addAttribute("productList", service.getProducts());
          return "webshoppage";
     }

     @PostMapping("/webshop")
     public String search(Model m){
          return "";
     }


}
