package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.webshop.entity.Category;
import se.iths.webshop.repository.CategoryRepo;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getCataegories(){
        return categoryRepo.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }

    public Category findById(int id){
        return categoryRepo.findById(id).get();
    }
}

