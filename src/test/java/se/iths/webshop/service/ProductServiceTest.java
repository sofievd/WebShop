package se.iths.webshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.webshop.entity.Category;
import se.iths.webshop.entity.Product;
import se.iths.webshop.repository.CategoryRepo;
import se.iths.webshop.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>ProductServiceTest</h2>
 * @date 2024-04-09
 */

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepo productRepo;
    @Mock
    private CategoryRepo categoryRepo;

    private ProductService productService;
    private Category category1, category2;
    private Product product1, product2, product3;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepo, categoryRepo);

        category1 = new Category("test category 1");
        category2 = new Category("test category 2");

        product1 = new Product(1, "test product 1", 30.2, category1,
                        "some random description 1", "test brand 1");
        product2 = new Product(2, "test product 2", 44.5, category1,
                        "some random description 2", "test brand 2");
        product3 = new Product(3, "test product 3", 18.0, category2,
                        "some random description 3", "test brand 2");
    }

    @Test
    void getProducts() {
        when(productRepo.findAll()).thenReturn(List.of(product1, product2, product3));
        List<Product> allProducts = productService.getProducts();

        verify(productRepo, atMostOnce()).findAll();
        verify(productRepo, times(1)).findAll();
        assertEquals(3, allProducts.size());
    }

    @Test
    void getProductByCategory() {
        when(categoryRepo.findByName(any(String.class))).thenReturn(category1);
        when(productRepo.findAllByCategory(category1)).thenReturn(List.of(product1, product2));

        List<Product> productsInSameCategoryList = productService.getProductByCategory("test category 1");

        verify(productRepo, atMostOnce()).findAllByCategory(any(Category.class));
        verify(categoryRepo, atMostOnce()).findByName(any(String.class));
        assertEquals(2, productsInSameCategoryList.size());
        assertEquals("test category 1", productsInSameCategoryList.get(1).getCategory().getName());
    }

    @Test
    void searchProducts() {
        when(productRepo.findAll()).thenReturn(List.of(product1, product2, product3));

        List<Product> productsFoundList = productService.searchProducts("test");

        assertEquals(3, productsFoundList.size());
        assertEquals("test product 1", productsFoundList.get(0).getName());
        verify(productRepo, atMost(1)).findAll();
    }

    @Test
    void saveProduct() {
        when(productRepo.save(any(Product.class))).thenReturn(product3);
        productService.saveProduct(product3);

        verify(productRepo, times(1)).save(product3);
    }

    @Test
    void findProductById() {
        when(productRepo.findById(any(Integer.class))).thenReturn(Optional.of(product1));

        Product product = productService.findProductById(1);
        assertEquals(product.getId(), product1.getId());
        assertEquals(1, product.getId());
        assertEquals("test product 1", product.getName());
        verify(productRepo, times(1)).findById(any(Integer.class));
    }
}