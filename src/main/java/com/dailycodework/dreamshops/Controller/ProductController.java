package com.dailycodework.dreamshops.Controller;

import com.dailycodework.dreamshops.Services.ProductService.IProductService;
import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.model.Product;
import com.dailycodework.dreamshops.request.AddProductRequest;
import com.dailycodework.dreamshops.request.ProductUpdateRequest;
import com.dailycodework.dreamshops.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
    }

    @GetMapping("product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product fetched successfully", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product added successfully", savedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/update/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request,
                                                     @PathVariable Long productId){
        try {
            Product updatedProduct = productService.updateProduct(request, productId);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", updatedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName,
                                                                 @RequestParam String productName){
        try {
            List<Product> products = productService.getProductByBrandAndName(brandName, productName);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Product fetched successfully", products));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try{
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }
    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name){
        try{
            List<Product> products = productService.getProductsByName(name);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("error", e.getMessage()));
        }
    }
    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand){
        try{
        List<Product> products = productService.getProductsByBrand(brand);
        if(products.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse("No products found", null));
        }
        return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
    } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/products/{category}/all/category")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable Product category){
        try{
            List<Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
