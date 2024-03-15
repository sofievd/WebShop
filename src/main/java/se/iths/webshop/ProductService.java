package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getProducts(){
        return productRepo.findAll();

    }

    public List<String> getCataegories(){
        List<Product> productList = productRepo.findAll();
        List<String> categoryList = null;
        for(Product p: productList){
            categoryList.add(p.getCategory());
        }
        return categoryList;
    }


}
