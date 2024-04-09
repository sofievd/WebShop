package se.iths.webshop.service;

import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import se.iths.webshop.entity.Category;
import se.iths.webshop.repository.CategoryRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {
    CategoryService categoryService;
    MockRepo repo;
    @BeforeEach
    void setUp(){
        repo = new MockRepo();
        categoryService = new CategoryService(repo);

    }

    @Test
    void getCataegories() {
        List<Category> list = categoryService.getCataegories();
        List<Category> categoryList = new ArrayList<>();
        Category category1 = new Category("Lace");
        Category category2 = new Category("light weight");
        Category category3 = new Category("super light weight");

        Collections.addAll(categoryList, category1,category2,category3);

        for(int i = 0; i<list.size(); i ++){
            assertEquals(list.get(i).toString(), categoryList.get(i).toString());
        }
    }

    @Test
    void getCategoryByName() {
        Category category = categoryService.getCategoryByName("lace");
        Category expectedCategory = new Category("lace");
        assertEquals(expectedCategory.toString(), category.toString());

    }

    @Test
    void findById() {
        Category category = categoryService.findById(1);
        Category expectedCategory = new Category("lace", 1);
        assertEquals(expectedCategory.toString(), category.toString());
    }

    class MockRepo implements CategoryRepo {
        public Category findByName(String name){
            return new Category(name);
        }

        @Override
        public <S extends Category> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Category> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Category> findById(Integer integer) {
            Category category = new Category("lace", 1);
            Optional<Category> optionalCategory = Optional.of(category);
            return optionalCategory;
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        public List<Category> findAll(){
            List<Category> categoryList = new ArrayList<>();
            Category category1 = new Category("Lace");
            Category category2 = new Category("light weight");
            Category category3 = new Category("super light weight");

            Collections.addAll(categoryList, category1,category2,category3);
            return categoryList;
        }

        @Override
        public List<Category> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Category entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Category> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Category> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Category> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Category getOne(Integer integer) {
            return null;
        }

        @Override
        public Category getById(Integer integer) {
            return null;
        }

        @Override
        public Category getReferenceById(Integer integer) {
            return null;
        }

        @Override
        public <S extends Category> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Category> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Category> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Category> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

        @Override
        public List<Category> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Category> findAll(Pageable pageable) {
            return null;
        }
    }
}