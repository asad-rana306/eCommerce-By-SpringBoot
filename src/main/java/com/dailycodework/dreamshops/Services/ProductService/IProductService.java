package com.dailycodework.dreamshops.Services.ProductService;

import com.dailycodework.dreamshops.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product getProductById(Long id);
    void deleteProduct(Long id);
    void updateProduct(Product product, Long Productid);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Product category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
