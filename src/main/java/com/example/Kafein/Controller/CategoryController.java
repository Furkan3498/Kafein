package com.example.Kafein.Controller;

import com.example.Kafein.Entities.Category;
import com.example.Kafein.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/getAll")
    public List<Category> getAllCategory(){
        return categoryService.getAll();
    }

    @PostMapping("/add/{warehouseId}")
    public Category createCategory(@RequestBody Category category, @PathVariable Integer warehouseId ) {
        return categoryService.create(category,warehouseId);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId, @RequestBody Category categoryDetails) {
        return ResponseEntity.ok(categoryService.update(categoryDetails, categoryId));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory (@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.delete(categoryId));
    }
}
