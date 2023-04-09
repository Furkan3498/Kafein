package com.example.Kafein.Controller;

import com.example.Kafein.Entities.WareHouse;
import com.example.Kafein.Service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouse")
@AllArgsConstructor
public class WarehouseController {

    @Autowired
    private final WarehouseService warehouseService;

    @GetMapping("/getAll")
    public List<WareHouse> getAllWarehouse(){
        return warehouseService.getAll();
    }

    @PostMapping("/add")
    public WareHouse createWarehouse(@RequestBody WareHouse warehouse) {
        return warehouseService.create(warehouse);
    }

    @GetMapping("/get/{warehouseId}")
    public ResponseEntity<WareHouse> getWarehouseById(@PathVariable Integer warehouseId) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(warehouseId));
    }

    @PutMapping("/update/{warehouseId}")
    public ResponseEntity<WareHouse> updateWarehouse(@PathVariable Integer warehouseId, @RequestBody WareHouse warehouseDetails) {
        return ResponseEntity.ok(warehouseService.update(warehouseDetails, warehouseId));
    }

    @DeleteMapping("/delete/{warehouseId}")
    public ResponseEntity<Map<String, Boolean>> deleteWarehouse(@PathVariable Integer warehouseId) {
        return ResponseEntity.ok(warehouseService.delete(warehouseId));
    }
}
