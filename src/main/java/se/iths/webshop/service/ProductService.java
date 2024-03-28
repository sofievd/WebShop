package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;
import se.iths.webshop.repository.CategoryRepo;
import se.iths.webshop.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
   @Autowired
    private ProductRepo productRepo;
   @Autowired
    private CategoryRepo categoryRepo;

    public List<Product> getProducts(){
        return productRepo.findAll();
    }
    public List<Product> getProductByCategory(String categoryString){
         Category category = categoryRepo.findByName(categoryString);
         List<Product> productsList = productRepo.findAllByCategory(category);
         return productsList;
    }

    public List<Product> searchProducts(String name){
        return productRepo.findByName(name);
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public Product findProductById(int id) {
        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isPresent()) {
            return opProduct.get();
        }
        return null;
    }
}
