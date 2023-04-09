package com.example.Kafein.Repository;

import com.example.Kafein.Entities.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<WareHouse, Integer> {
}
