package com.example.Kafein.Service;

import com.example.Kafein.Entities.Category;
import com.example.Kafein.Entities.Product;
import com.example.Kafein.Exception.FullShelfException;
import com.example.Kafein.Exception.ResourceNotFoundException;
import com.example.Kafein.Repository.CategoryRepository;
import com.example.Kafein.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ProductRepository productRepository;


    public Product create(Product product, int categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Shelf Not Found."));
        if(category.getProducts().size()  == 4) {
            throw new FullShelfException("All the places on the category are full");
        }
        product.setCategory(category);
        log.info("Creating Product");
        return productRepository.save(product);
    }

    public List<Product> getAll(){
        log.info("Get All Product");
        return productRepository.findAll();
    }

    public List<Product> findByBarcode(String barcode){
        log.info("Get Requesting Products by Borcode");
        return productRepository.findByBarcode(barcode);
    }

    public Product getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        log.info("Find By Category");
        return product;
    }

    public Product update(Product updatedProduct, int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        product.setCode(updatedProduct.getCode());
        product.setDescription(updatedProduct.getDescription());
        product.setBarcode(updatedProduct.getBarcode());

        Product upProduct = productRepository.save(product);
        log.info("Updating Product");
        return upProduct;
    }

    public Map<String, Boolean> delete(int id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        Map<String, Boolean> response = new HashMap<>();
        productRepository.delete(product);
        response.put("deleted", Boolean.TRUE);
        log.info("Deleting Product");
        return response;
    }
}
