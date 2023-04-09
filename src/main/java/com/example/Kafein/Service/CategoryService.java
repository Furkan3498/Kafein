package com.example.Kafein.Service;

import com.example.Kafein.Entities.Category;
import com.example.Kafein.Entities.WareHouse;
import com.example.Kafein.Exception.AssociatedDataException;
import com.example.Kafein.Exception.ResourceNotFoundException;
import com.example.Kafein.Repository.CategoryRepository;
import com.example.Kafein.Repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class CategoryService {
    private final WarehouseRepository warehouseRepository;
    private final CategoryRepository categoryRepository;


    public Category create(Category category, int warehouseId) {
        WareHouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found."));
//		if(shelf.getId() == null) {
//			throw new ResourceNotFoundException("Category Not Found");
//		}
        category.setWarehouse(warehouse);
        log.info("Creating Category");
        return categoryRepository.save(category);
    }

    public List<Category> getAll(){
        log.info("Get All Category");
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        log.info("Find By Category");
        return category;
    }

    public Category update(Category updatedCategory, int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        category.setCode(updatedCategory.getCode());
        category.setDescription(updatedCategory.getDescription());

        Category uptCategory= categoryRepository.save(category);
        log.info("Updating Category");
        return uptCategory;
    }

    public Map<String, Boolean> delete(int id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
        if(category.getProducts().size() > 0) {
            throw new AssociatedDataException("Associated data cannot be deleted");
        }
        Map<String, Boolean> response = new HashMap<>();
        categoryRepository.delete(category);
        response.put("deleted", Boolean.TRUE);
        log.info("Deleting Category");
        return response;
    }
}
