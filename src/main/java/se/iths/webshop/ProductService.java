package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
   @Autowired
    private ProductRepo productRepo;
   @Autowired
    private  CategoryRepo categoryRepo;

    public List<Product> getProducts(){
        return productRepo.findAll();

    }
    public List<Product> getProductByCategory(String categoryString){
         Category category = categoryRepo.findByName(categoryString);
         List<Product> productsList = productRepo.findAllByCategory(category);
         return productsList;
    }

    public Optional<Product> searchProducts(String name){
        Optional<Product> product = productRepo.findByName(name);
        return product;
    }


}
