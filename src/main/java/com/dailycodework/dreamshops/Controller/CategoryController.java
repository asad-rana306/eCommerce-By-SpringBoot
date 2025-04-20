package com.dailycodework.dreamshops.Controller;

import com.dailycodework.dreamshops.Services.Category.ICategoryService;
import com.dailycodework.dreamshops.model.Category;
import com.dailycodework.dreamshops.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    public final ICategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found!",categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:",INTERNAL_SERVER_ERROR));

        }
    }
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name){
        Category theCategory = categoryService.addCategory(name);
        return ResponseEntity.ok(new ApiResponse("Success",theCategory ));

    }
}
