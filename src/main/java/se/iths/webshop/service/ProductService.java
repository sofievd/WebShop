package se.iths.webshop.service;

import org.springframework.stereotype.Service;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;
import se.iths.webshop.repository.CategoryRepo;
import se.iths.webshop.repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * @author Sofie Van Dingenen, Depinder Kaur
 * @version 0.1
 * <h2> ProductService </h2></>
 * @date 2024-04-08
 */

@Service
public class ProductService {

    private ProductRepo productRepo;

    private CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public List<Product> getProductByCategory(String categoryString) {
        Category category = categoryRepo.findByName(categoryString);
        List<Product> productsList = productRepo.findAllByCategory(category);
        return productsList;
    }

    public List<Product> searchProducts(String searchWord) {
        List<Product> allProducts = productRepo.findAll();
        List<Product> productsfound = new ArrayList<>();
        for(int i = 0; i < allProducts.size(); i++ ){
            String name = allProducts.get(i).getName().toLowerCase();
            String searchWordBegin = searchWord.toLowerCase().substring(0,3);
            if(name.contains(searchWordBegin)){
                productsfound.add(allProducts.get(i));
            }
        }
        return productsfound;
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
