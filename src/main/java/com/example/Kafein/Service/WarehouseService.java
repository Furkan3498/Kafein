package com.example.Kafein.Service;

import com.example.Kafein.Entities.WareHouse;
import com.example.Kafein.Exception.AssociatedDataException;
import com.example.Kafein.Exception.ResourceNotFoundException;
import com.example.Kafein.Repository.WarehouseRepository;
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
public class WarehouseService {

    @Autowired
    private final WarehouseRepository warehouseRepository;

//	@Autowired //Constructor Injection
//	public WarehouseService(WarehouseRepository warehouseRepository) {
//		this.warehouseRepository = warehouseRepository;
//	}

    public WareHouse create(WareHouse warehouse) {
//		if(warehouse.getId() == null) {
//			throw new ResourceNotFoundException("Warehouse not found");
//		}
        log.info("Inserting Warehouse");
        return warehouseRepository.save(warehouse);
    }

    public List<WareHouse> getAll(){
        log.info("Get All Warehouse");
        return warehouseRepository.findAll();
    }

    public WareHouse getWarehouseById(int id) {
        WareHouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found"));
        log.info("Find By Warehouse");
        return warehouse;
    }

    public WareHouse update(WareHouse updatedWarehouse, int id) {
        WareHouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found"));

        warehouse.setName(updatedWarehouse.getName());
        warehouse.setCode(updatedWarehouse.getCode());
        warehouse.setDescription(updatedWarehouse.getDescription());
        warehouse.setAddress(updatedWarehouse.getAddress());

        WareHouse uptWarehouse = warehouseRepository.save(warehouse);
        log.info("Updating Warehouse");
        return uptWarehouse;
    }

    public Map<String, Boolean> delete(int id){
        WareHouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found"));
        if(warehouse.getCategoryList().size() > 0) {
            throw new AssociatedDataException("Associated data cannot be deleted");
        }
        Map<String, Boolean> response = new HashMap<>();
        warehouseRepository.delete(warehouse);
        response.put("deleted", Boolean.TRUE);
        log.info("Deleting Warehouse");
        return response;
    }
}
