package com.example.Kafein.Controller;

import com.example.Kafein.Entities.Product;
import com.example.Kafein.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getAllProduct(){
        return productService.getAll();
    }

    @PostMapping("/add/{categoryId}")
    public Product createProduct(@RequestBody Product product, @PathVariable Integer categoryId ) {
        return productService.create(product,categoryId);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product productDetails) {
        return ResponseEntity.ok(productService.update(productDetails, productId));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.delete(productId));
    }

    @GetMapping("/{barcode}")
    public List<Product> getAllProduct(@PathVariable String barcode){
        return productService.findByBarcode(barcode);
    }
}
