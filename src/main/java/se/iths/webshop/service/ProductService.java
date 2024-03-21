package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.webshop.repository.CategoryRepo;
import se.iths.webshop.repository.ProductRepo;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;

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

    public Optional<Product> searchProducts(String name){
        Optional<Product> product = productRepo.findByName(name);
        return product;
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public List<Product> findByNameAndCategoryAndBrand(String name, Category category, String brand) {
        return productRepo.findByNameAndCategoryAndBrand(name, category, brand);
    }

    public Product findProductById(int id) {
        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isPresent()) {
            return opProduct.get();
        }
        return null;
    }
}
