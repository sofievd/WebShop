package se.iths.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private  CategoryRepo categoryRepo;

    public List<Category> getCataegories(){
        return categoryRepo.findAll();
    }
}
