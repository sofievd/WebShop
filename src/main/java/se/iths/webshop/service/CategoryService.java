package se.iths.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.webshop.entity.Category;
import se.iths.webshop.repository.CategoryRepo;

import java.util.List;

/**
 * @author Sofie Van Dingenen
 * @version 0.1
 * <h2> CategoryService </h2></>
 * @date 2024-04-08
 */

@Service
public class CategoryService {

    private CategoryRepo categoryRepo;
@Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getCataegories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }

    public Category findById(int id) {
        return categoryRepo.findById(id).get();
    }
}

